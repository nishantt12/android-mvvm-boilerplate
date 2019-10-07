package com.foodora.model

class ApiResponse<T> {
  var isError: Boolean? = null
  var message: String? = null
  var data: T? = null
  var error: String? = null
}