package com.lnsergey.softbalance.app.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class WhetherResponse {
    @SerializedName("lat")
    @Expose
    var lat: Double? = null

    @SerializedName("lon")
    @Expose
    var lon: Double? = null

    @SerializedName("timezone")
    @Expose
    var timezone: String? = null

    @SerializedName("timezone_offset")
    @Expose
    var timezoneOffset: Int? = null

    @SerializedName("daily")
    @Expose
    var daily: List<Daily>? = null

}