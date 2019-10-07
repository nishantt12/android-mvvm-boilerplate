package com.foodora.util

import android.content.Context
import javax.inject.Inject
import androidx.annotation.StringRes


class ResourceProvider @Inject constructor(var context: Context) {

  fun getString(@StringRes resId: Int): String {
    return context.getString(resId)
  }
}