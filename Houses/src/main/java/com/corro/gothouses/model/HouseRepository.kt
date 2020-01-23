package com.corro.gothouses.model

import androidx.lifecycle.MutableLiveData
import com.corro.gothouses.model.retrofit.HouseApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HouseRepository(private val houseApi: HouseApi) {
    var houseList = MutableLiveData<ArrayList<House>>()

    suspend fun loadHouses() {
        var page = 0
        val cachingList = ArrayList<House>()
        val listComplete = ArrayList<House>()

        do {
            page++
            cachingList.clear()
            cachingList.addAll(houseApi.getHouses(page, 50).await())
            listComplete.addAll(cachingList)
        } while (cachingList.size == 50)

        withContext(Dispatchers.Main) {
            houseList.value = listComplete
        }
    }
}