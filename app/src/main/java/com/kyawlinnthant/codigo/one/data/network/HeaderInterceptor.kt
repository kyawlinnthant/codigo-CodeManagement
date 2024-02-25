package com.kyawlinnthant.codigo.one.data.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder().addHeader(
            "Authorization",
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5ZWE3YmE0MjcxMTI1MjA4MzJmNWRhMmFmM2E0N2JkNCIsInN1YiI6IjY0NWExMzZkMTU2Y2M3MDEzZmYyMWIxZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4ec3-tZUlQrdKQsKID9Wt904Slj_-0H4Gdue2v3ujD0"
        )
        return chain.proceed(builder.build())
    }
}
