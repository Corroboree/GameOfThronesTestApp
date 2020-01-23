package com.corro.gothouses.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corro.gothouses.model.HouseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HouseViewModel(private val houseRepository: HouseRepository) : ViewModel() {

    val houseList = houseRepository.houseList

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.Default) {
            houseRepository.loadHouses()
        }
    }
}