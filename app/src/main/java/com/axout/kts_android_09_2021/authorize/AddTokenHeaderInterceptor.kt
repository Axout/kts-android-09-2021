package com.axout.kts_android_09_2021.authorize

import okhttp3.Interceptor
import okhttp3.Response

class AddTokenHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val token = AuthToken.token
        if (token != null) {
            original = original.newBuilder()
                .header("Authorization", "Bearer $token")
                .method(original.method, original.body)
                .build()
        }
        return chain.proceed(original)
    }
}