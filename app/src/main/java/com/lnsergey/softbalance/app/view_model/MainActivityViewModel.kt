package com.lnsergey.softbalance.app.view_model

import android.annotation.SuppressLint
import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.lnsergey.softbalance.R
import com.lnsergey.softbalance.app.App
import com.lnsergey.softbalance.app.data.api.LocationService
import com.lnsergey.softbalance.app.data.api.WhetherService
import com.lnsergey.softbalance.app.data.model.Daily
import com.lnsergey.softbalance.app.data.model.LocationResponse
import com.lnsergey.softbalance.app.data.model.WhetherResponse
import com.lnsergey.softbalance.app.ui.adapter.ForecastRecyclerAdapter
import com.lnsergey.softbalance.utils.isOnline
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class MainActivityViewModel : ViewModel() {

    @Inject lateinit var appContext: Context
    @Inject lateinit var whetherService: WhetherService
    @Inject lateinit var locationService: LocationService

    var forecastRecyclerAdapter: ForecastRecyclerAdapter
    val searchInput = ObservableField<String>("")
    val message = ObservableField<String>("")
    val loading = ObservableBoolean(false)

    init {
        App.appComponent.inject(this)
        forecastRecyclerAdapter = ForecastRecyclerAdapter(arrayListOf())
    }

    @SuppressLint("CheckResult")
    fun searchLocation() {
        loading.set(true)
        forecastRecyclerAdapter.setData(arrayListOf())
        if (!isOnline(appContext)) {
            message.set(appContext.getString(R.string.error_connection))
            return
        }
        try {
            locationService.getLocationByName(searchInput.get()!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<LocationResponse>() {
                    override fun onSuccess(t: LocationResponse) {
                        try {
                            getWhetherForecast(mapOf(
                                "lat" to t.results!![0].geometry!!.lat!!,
                                "lng" to t.results!![0].geometry!!.lng!!
                            ))
                        } catch (e: IndexOutOfBoundsException) {
                            message.set(appContext.getString(R.string.http_error_404))
                            loading.set(false)
                        } catch (e: Exception) {
                            message.set(appContext.getString(R.string.error_base))
                            loading.set(false)
                        }
                    }
                    override fun onError(e: Throwable) {
                        loading.set(false)
                        message.set(
                            when ((e as HttpException).code()) {
                                400 ->  appContext.getString(R.string.http_error_400)
                                401 ->  appContext.getString(R.string.http_error_401)
                                403 ->  appContext.getString(R.string.http_error_403)
                                404 ->  appContext.getString(R.string.http_error_404)
                                in 500..599 ->  appContext.getString(R.string.http_error_5xx)
                                else ->  appContext.getString(R.string.error_base)
                            }
                        )
                    }

                })
        } catch (e: NullPointerException) {
            message.set(appContext.getString(R.string.error_base))
        }
    }

    @SuppressLint("CheckResult")
    fun getWhetherForecast(locationResponse: Map<String, Double>) {
        if (locationResponse["lat"] == null || locationResponse["lng"] == null) {
            loading.set(false)
            message.set(appContext.getString(R.string.error_base))
            return
        }
        if (!isOnline(appContext)) {
            message.set(appContext.getString(R.string.error_connection))
            return
        }
        try {
            whetherService.getWhetherForecast(
                lat = locationResponse["lat"]!!,
                lon = locationResponse["lng"]!!
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WhetherResponse>() {
                    override fun onSuccess(t: WhetherResponse) {
                        loading.set(false)
                        try {
                            forecastRecyclerAdapter.setData(t.daily as ArrayList<Daily>)
                            message.set("")
                        } catch (e: NullPointerException) {
                            message.set(appContext.getString(R.string.error_base))
                        }
                    }
                    override fun onError(e: Throwable) {
                        loading.set(false)
                        message.set(
                            when ((e as HttpException).code()) {
                                400 ->  appContext.getString(R.string.http_error_400)
                                401 ->  appContext.getString(R.string.http_error_401)
                                403 ->  appContext.getString(R.string.http_error_403)
                                404 ->  appContext.getString(R.string.http_error_404)
                                in 500..599 ->  appContext.getString(R.string.http_error_5xx)
                                else ->  appContext.getString(R.string.error_base)
                            }
                        )
                    }

                })
        } catch (e: Exception) {
            message.set(appContext.getString(R.string.error_base))
            e.printStackTrace()
        }
    }

}