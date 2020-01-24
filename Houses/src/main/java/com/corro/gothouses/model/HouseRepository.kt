package com.corro.gothouses.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.corro.gothouses.model.retrofit.HouseApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HouseRepository(private val houseApi: HouseApi) {
    private var cache: HashMap<Int, House>? = null
    private val _houseList = MutableLiveData<List<House>>()
    val houseList: LiveData<List<House>>
        get() = _houseList

    suspend fun refresh() {
        var page = 0
        var entry = 0
        val cachingList = ArrayList<House>()
        cache = HashMap()

        do {
            page++
            cachingList.clear()
            cachingList.addAll(houseApi.getHouses(page, 50))
            for (house in cachingList) {
                entry++
                cache!![entry] = house
            }
        } while (cachingList.size == 50)

        withContext(Dispatchers.Main) {
            _houseList.value = ArrayList<House>(cache!!.values)
        }
    }

    fun getHouseDetails(id: Int): House? {
        return cache?.get(id)
    }
}