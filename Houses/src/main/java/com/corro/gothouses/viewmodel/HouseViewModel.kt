package com.corro.gothouses.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corro.gothouses.model.House
import com.corro.gothouses.model.HouseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HouseViewModel(private val houseRepository: HouseRepository) : ViewModel() {

    private val _loadingStatus = MutableLiveData<Status>()
    val loadingStatus: LiveData<Status>
        get() = _loadingStatus

    init {
        refresh()
    }

    fun refresh() {
        _loadingStatus.value = Status.StartLoading
        viewModelScope.launch {
            try{
                delay(2500)
                _loadingStatus.value = Status.FinishLoading(withContext(Dispatchers.Default){houseRepository.loadHouses()})
            }catch(e: Exception){
                e.printStackTrace()
                _loadingStatus.value = Status.ErrorLoading(e.message)
            }
        }
    }

    sealed class Status {
        object StartLoading : Status()
        data class FinishLoading(val list: List<House>) : Status()
        data class ErrorLoading(val error: String?) : Status()
    }
}