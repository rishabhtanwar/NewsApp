package com.rishabh.news.activities

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.rishabh.news.R
import com.rishabh.news.util.Util


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
}