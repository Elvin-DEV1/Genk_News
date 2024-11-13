package com.example.genknews.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.genknews.common.helper.InternetObserver
import com.example.genknews.common.logger.Logger
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {
    /* **********************************************************************
     * Variable
     ********************************************************************** */
    @Inject
    protected lateinit var logger: Logger

    @Inject
    protected lateinit var internetObserver: InternetObserver

    /* **********************************************************************
     * Function - Lifecycle
     ********************************************************************** */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.i("Register observer network")
        internetObserver.observe(this) { isConnected ->
            logger.d("Internet available = $isConnected")
            onNetworkChange(isConnected)
        }
        logger.i("Init permission repository")
    }

    /* **********************************************************************
     * Function - Abstract
     ********************************************************************** */
    abstract fun onNetworkChange(isConnected: Boolean)
}