package com.lnsergey.softbalance.app.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import java.text.DateFormat
import java.util.*


class Daily {
    @SerializedName("dt")
    @Expose
    var dt: Int? = null

    @SerializedName("sunrise")
    @Expose
    var sunrise: Int? = null

    @SerializedName("sunset")
    @Expose
    var sunset: Int? = null

    @SerializedName("temp")
    @Expose
    var temp: Temp? = null

    @SerializedName("feels_like")
    @Expose
    var feelsLike: JSONObject? = null

    @SerializedName("pressure")
    @Expose
    var pressure: Double? = null

    @SerializedName("humidity")
    @Expose
    var humidity: Double? = null

    @SerializedName("dew_point")
    @Expose
    var dewPoint: Double? = null

    @SerializedName("wind_speed")
    @Expose
    var windSpeed: Double? = null

    @SerializedName("wind_deg")
    @Expose
    var windDeg: Double? = null

    @SerializedName("weather")
    @Expose
    var weather: List<JSONObject>? = null

    @SerializedName("clouds")
    @Expose
    var clouds: Double? = null

    @SerializedName("pop")
    @Expose
    var pop: Double? = null

    @SerializedName("rain")
    @Expose
    var rain: Double? = null

    @SerializedName("uvi")
    @Expose
    var uvi: Double? = null

    fun dtToString() : String = try {
            val df: DateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM)
            val netDate = Date(dt!!*1000.toLong())
            df.format(netDate)
        } catch (e: NullPointerException) { "" }

    fun temperatureToString() = temp?.day?.toInt().toString()

}