package com.example.memesfeed.application.helpers

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import javax.inject.Inject

class Util @Inject constructor(private val context: Context) {

    fun isInternetConnection(): Boolean {

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT < 23) {

            val networkInfo = cm.activeNetworkInfo

            if (networkInfo != null) {
                return networkInfo.isConnected && (networkInfo.type == ConnectivityManager.TYPE_WIFI || networkInfo.type == ConnectivityManager.TYPE_MOBILE)
            }
        } else {

            val network = cm.activeNetwork

            if (cm.activeNetwork != null) {
                val nc = cm.getNetworkCapabilities(network)
                return if (nc != null) {
                    nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                } else {
                    false
                }
            }
        }
        return false
    }

}