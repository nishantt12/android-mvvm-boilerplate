package com.foodora.util

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.foodora.BuildConfig

object Util {

  fun L(TAG: String? = "Foodora-response", message: String?) {
    if (BuildConfig.DEBUG)
      Log.e(TAG, message)
  }

  fun isNullOrEmptyString(text: CharSequence?): Boolean {
    return text == null || TextUtils.isEmpty(text) || text.length == 0
  }

  fun isValidEmail(email: String?): Boolean {
    if (!isNullOrEmptyString(email)) {
      return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    return false
  }

  fun isValidMobileNo(mobile: String?): Boolean {
    if (!isNullOrEmptyString(mobile) || mobile?.length == 10) {
      return Patterns.PHONE.matcher(mobile).matches()
    }
    return false
  }

  fun showToast(message: String?, context: Context?) {
    Toast.makeText(context, message, LENGTH_SHORT).show()
  }
}
