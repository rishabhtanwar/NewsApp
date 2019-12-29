package com.funtoolearn.util

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException


object Util {

    fun showToast(message: String?, context: Context?) {
        Toast.makeText(context, message, LENGTH_LONG).show()
    }
}
