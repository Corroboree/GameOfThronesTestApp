package com.corro.gothouses.model.retrofit

import com.corro.gothouses.model.House
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface HouseApi {

    @GET("houses")
    suspend fun getHouses(@Query("page") page: Int, @Query("pageSize") pageSize: Int): List<House>
}