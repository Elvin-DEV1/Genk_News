package com.example.genknews.common.base

import androidx.lifecycle.ViewModel
import com.example.genknews.common.logger.Logger
import com.google.gson.Gson
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    /* **********************************************************************
     * Variable
     ********************************************************************** */
    @Inject
    protected lateinit var logger: Logger

    @Inject
    protected lateinit var gson: Gson
}