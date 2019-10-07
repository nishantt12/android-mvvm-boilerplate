package com.foodora.base

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.foodora.R
import com.foodora.util.Util

abstract class BaseActivity : AppCompatActivity() {
  private var mContext: Context? = null
  private var progressDialog: ProgressDialog? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mContext = this
  }

  fun showToast(message: String? = mContext?.getString(R.string.somehting_went_wrong)) {
    mContext?.let { Util.showToast(message, mContext) }
  }

  fun showProgressDialog() {
    if (progressDialog == null) progressDialog = ProgressDialog(mContext)
    progressDialog?.setMessage(getString(R.string.loading))
    progressDialog?.show()
  }

  fun hideProgressDialog() {
    if (progressDialog != null) progressDialog?.dismiss()
  }

  fun startActivityFlagClearTask(intent: Intent?) {
    intent?.let {
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      startActivity(intent)
    }
  }
}