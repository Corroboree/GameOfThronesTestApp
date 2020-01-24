package com.corro.gothouses.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corro.gothouses.model.House
import com.corro.gothouses.model.HouseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HouseViewModel(private val houseRepository: HouseRepository) : ViewModel() {

    private val _loadingStatus = MutableLiveData<Status>()
    val loadingStatus: LiveData<Status>
        get() = _loadingStatus

    init {
        houseRepository.houseList.observeForever {
            _loadingStatus.value = Status.FinishLoadingList(it)
        }
    }

    fun requestHouseList() = viewModelScope.launch {
        _loadingStatus.value = Status.StartLoading
        try {
            launch(Dispatchers.Default) { houseRepository.refresh() }
        }
        catch (e: Exception) {
            e.printStackTrace()
            _loadingStatus.value = Status.ErrorLoading(e.message)
        }
    }

    fun loadHouseDetails(id: Int) {
        _loadingStatus.value = Status.StartLoading
        val details = houseRepository.getHouseDetails(id)
        _loadingStatus.value = if (details != null) {
            Status.FinishLoadingDetails(details)
        } else {
            Status.ErrorLoading("")
        }
    }

    sealed class Status {
        object StartLoading : Status()
        data class FinishLoadingList(val list: List<House>) : Status()
        data class FinishLoadingDetails(val house: House) : Status()
        data class ErrorLoading(val error: String?) : Status()
    }
}