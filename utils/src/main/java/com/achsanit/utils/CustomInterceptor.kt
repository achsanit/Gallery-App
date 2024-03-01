package com.achsanit.utils

import okhttp3.Interceptor
import okhttp3.Response

class CustomInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder() // create new chain request
        val originalUrl = chain.request().url() // get base url
        val urlWithApiKey = originalUrl.newBuilder()
            .addQueryParameter("client_id", "jcVyC9k3tj5wnMj-8C8Hy7gSqxEOZyK_zrxLhyvd12Y") // set client id to parameter
            .build()

        request.url(urlWithApiKey) // set url request with newUrl *url with parameter

        return chain.proceed(request.build()) // return request with parameter that already set
    }
}