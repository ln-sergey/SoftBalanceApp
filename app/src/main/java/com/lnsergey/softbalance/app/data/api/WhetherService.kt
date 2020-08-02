package com.lnsergey.softbalance.app.data.api

import com.lnsergey.softbalance.config.WhetherBaseUrl
import com.lnsergey.softbalance.app.data.model.WhetherResponse
import com.lnsergey.softbalance.config.WhetherToken
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WhetherService {

    @GET("/data/2.5/onecall")
    fun getWhetherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String = "current,minutely,hourly",
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = WhetherToken
    ) : Single<WhetherResponse>

}