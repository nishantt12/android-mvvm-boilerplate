package com.foodora.util

import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("app:onEditorAction")
fun bindOnEditorAction(editText: EditText?,
    onEditorActionListener: TextView.OnEditorActionListener?) {
  editText?.setOnEditorActionListener(onEditorActionListener)
}

@BindingAdapter("app:onTextChange")
fun bindOnTextChange(editText: EditText?, textWatcher: TextWatcher) {
  editText?.addTextChangedListener(textWatcher)
}
