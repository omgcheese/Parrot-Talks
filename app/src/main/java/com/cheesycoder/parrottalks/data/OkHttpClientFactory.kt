package com.cheesycoder.parrottalks.data

import okhttp3.OkHttpClient

class OkHttpClientFactory {
    fun create(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
}