package com.foodora.util

import android.util.Log
import com.foodora.BuildConfig

object L {
    fun e(TAG: String? = "Foodora-response", message: String?) {
        if (BuildConfig.DEBUG)
            Log.e(TAG, message)
    }

    fun d(TAG: String? = "Foodora-response", message: String?) {
        if (BuildConfig.DEBUG)
            Log.d(TAG, message)
    }

    fun i(TAG: String? = "Foodora-response", message: String?) {
        if (BuildConfig.DEBUG)
            Log.i(TAG, message)
    }
}