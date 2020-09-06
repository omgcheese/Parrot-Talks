package com.cheesycoder.parrottalks.data

import com.cheesycoder.parrottalks.BuildConfig
import org.koin.dsl.module

val dataModule = module {
    single<EchoWebSocket> { EchoWebSocketImpl(BuildConfig.WEB_SOCKET_URL) }
}
