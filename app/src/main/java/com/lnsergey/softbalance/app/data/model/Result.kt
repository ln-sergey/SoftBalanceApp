package com.lnsergey.softbalance.app.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import org.json.JSONObject


class Result {
    @SerializedName("bounds")
    @Expose
    var bounds: JSONObject? = null

    @SerializedName("components")
    @Expose
    var components: JSONObject? = null

    @SerializedName("confidence")
    @Expose
    var confidence: Int? = null

    @SerializedName("formatted")
    @Expose
    var formatted: String? = null

    @SerializedName("geometry")
    @Expose
    var geometry: Geometry? = null

}