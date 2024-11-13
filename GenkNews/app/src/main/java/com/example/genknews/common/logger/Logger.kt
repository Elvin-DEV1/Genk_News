package com.example.genknews.common.logger

import android.content.Context
import android.util.Log
import com.example.genknews.BuildConfig
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Logger @Inject constructor() {
    /* **********************************************************************
     * Variable
     ********************************************************************** */
    enum class Type {
        VERBOSE, DEBUG, INFO, WARNING, ERROR;
    }

    private var tag: String? = Logger::class.simpleName
    private val level: Type = Type.VERBOSE
    private val backupTag = "Logger"

    /* **********************************************************************
     * Function - Public
     ********************************************************************** */
    fun v(message: String, tag: String? = this.tag) = print(Type.VERBOSE, tag, message)
    fun d(message: String, tag: String? = this.tag) = print(Type.DEBUG, tag, message)
    fun i(message: String, tag: String? = this.tag) = print(Type.INFO, tag, message)
    fun w(message: String, tag: String? = this.tag) = print(Type.WARNING, tag, message)
    fun e(message: String, tag: String? = this.tag) = print(Type.ERROR, tag, message)

    /* **********************************************************************
     * Function - Private
     ********************************************************************** */
    private fun print(logType: Type = Type.VERBOSE, tag: String? = "", message: String = "") {
        if (message.isEmpty()) return
        if (logType.ordinal < level.ordinal) return

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()

        this.tag = tag ?: backupTag
        val element = Throwable().stackTrace[3]
        val pack = BuildConfig.APPLICATION_ID
        val file = element.className.replace(pack, "")
        val method = element.methodName
        val line = element.lineNumber

        val data = "[$file] $method($line): $message"
        when (logType) {
            Type.VERBOSE -> Log.v(tag, data)
            Type.DEBUG -> Log.d(tag, data)
            Type.INFO -> Log.i(tag, data)
            Type.WARNING -> Log.w(tag, data)
            Type.ERROR -> Log.e(tag, data)
        }
    }
}