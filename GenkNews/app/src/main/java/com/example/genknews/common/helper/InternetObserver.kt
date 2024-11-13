package com.example.genknews.common.helper

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import com.example.genknews.common.logger.Logger
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class InternetObserver @Inject constructor(@ApplicationContext private val context: Context) :
    LiveData<Boolean>() {
    /* **********************************************************************
     * Variable
     ********************************************************************** */
    @Inject
    lateinit var logger: Logger
    private var internetBroadCast: BroadcastReceiver? = null
    private var filter: IntentFilter? = null
    private val receiverFlag = ContextCompat.RECEIVER_EXPORTED

    /* **********************************************************************
     * Companion
     ********************************************************************** */
    companion object {
        const val BROADCAST_ACTION = "android.net.conn.CONNECTIVITY_CHANGE"
    }

    /* **********************************************************************
     * Function - Override
     ********************************************************************** */
    override fun onActive() {
        super.onActive()
        registerBroadcast()
    }

    override fun onInactive() {
        super.onInactive()
        unregisterBroadcast()
    }

    /* **********************************************************************
     * Function
     ********************************************************************** */
    private fun registerBroadcast() {
        if (internetBroadCast == null) {
            internetBroadCast = object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    value = context?.let { isConnected(it) } == true
                }

            }
        }
        if (filter == null) {
            filter = IntentFilter().apply {
                addAction(BROADCAST_ACTION)
            }
        }
        ContextCompat.registerReceiver(context, internetBroadCast, filter!!, receiverFlag)
    }

    private fun isConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (network != null) {
            if (network.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                logger.i("NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (network.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                logger.i("NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (network.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                logger.i("NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }

    private fun unregisterBroadcast() {
        context.unregisterReceiver(internetBroadCast)
    }
}