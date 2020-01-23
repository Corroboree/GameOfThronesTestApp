package com.corro.gothouses.model

import com.corro.gothouses.model.retrofit.HouseApi

class HouseRepository(private val houseApi: HouseApi) {
    private lateinit var cache: List<House>

    suspend fun loadHouses(): List<House> {
        var page = 0
        val cachingList = ArrayList<House>()
        val listComplete = ArrayList<House>()

        do {
            page++
            cachingList.clear()
            cachingList.addAll(houseApi.getHouses(page, 50).await())
            listComplete.addAll(cachingList)
        } while (cachingList.size == 50)

        cache = listComplete
        return listComplete
    }
}