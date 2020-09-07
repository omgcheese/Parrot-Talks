package com.cheesycoder.parrottalks.viewmodel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { WebSocketViewModel(get()) }
}