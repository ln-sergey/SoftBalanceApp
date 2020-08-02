package com.lnsergey.softbalance.app.data.model

import android.provider.Telephony.Mms.Rate

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import org.json.JSONObject


class LocationResponse {
    @SerializedName("documentation")
    @Expose
    var documentation: String? = null

    @SerializedName("licenses")
    @Expose
    var licenses: List<JSONObject>? = null

    @SerializedName("rate")
    @Expose
    var rate: Rate? = null

    @SerializedName("results")
    @Expose
    var results: List<Result>? = null

    @SerializedName("status")
    @Expose
    var status: JSONObject? = null

    @SerializedName("stay_informed")
    @Expose
    var stayInformed: JSONObject? = null

    @SerializedName("thanks")
    @Expose
    var thanks: String? = null

    @SerializedName("timestamp")
    @Expose
    var timestamp: JSONObject? = null

    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null

}