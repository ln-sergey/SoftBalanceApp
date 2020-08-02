package com.lnsergey.softbalance.app.data.api

import com.lnsergey.softbalance.config.LocationBaseUrl
import com.lnsergey.softbalance.app.data.model.LocationResponse
import com.lnsergey.softbalance.config.LocationToken
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {

    @GET("/geocode/v1/json")
    fun getLocationByName(
        @Query("q") q: String,
        @Query("limit") limit: Int = 1,
        @Query("no_annotations") no_annotations: Int = 1,
        @Query("language") language: String = "native",
        @Query("key") key: String = LocationToken
    ) : Single<LocationResponse>

}