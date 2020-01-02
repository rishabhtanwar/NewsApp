package com.rishabh.news.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import com.bumptech.glide.Glide
import com.rishabh.news.util.Constants
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object Util {

    fun showToast(message: String?, context: Context?) {
        Toast.makeText(context, message, LENGTH_LONG).show()
    }

    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }

    fun loadGlideImage(context: Context, imageView: ImageView, url: String?) {
        url.let {
            Glide.with(context).load(url).into(imageView)
        }
    }

    fun serializeDateFromString(
        dateString: String?,
        format: String?
    ): Date? {
        val formater = SimpleDateFormat(format)
        var date: Date? = null
        try {
            date = formater.parse(dateString)
            println(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date
    }

    fun deserializeDate(date: Date?, format: String?): String? {
        val dateFormat = SimpleDateFormat(format)
        return dateFormat.format(date)
    }

    fun getErrorMessage(e: Throwable): String? {
        return try {
            if (e is HttpException) {
                val body: ResponseBody? = e.response().errorBody()
                val jsonObject = JSONObject(body?.string())
                jsonObject.getString("error")
            } else {
                Constants.ERROR_MESSAGE
            }

        } catch (e: Exception) {
            e.message
        }

    }
}
