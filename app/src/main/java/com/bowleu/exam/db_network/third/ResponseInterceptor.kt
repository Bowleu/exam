package com.bowleu.exam.db_network.third

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

class ResponseInterceptor : Interceptor {

    companion object {
        private const val TAG = "ResponseInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        Log.d(TAG, "URL: ${request.url}; Response code: ${response.code}")
        return response
    }
}