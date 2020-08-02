package com.lnsergey.softbalance.utils

import android.content.Context
import android.net.ConnectivityManager

fun isOnline(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.activeNetworkInfo != null
            && connectivityManager.activeNetworkInfo!!.isAvailable
            && connectivityManager.activeNetworkInfo!!.isConnected
}

