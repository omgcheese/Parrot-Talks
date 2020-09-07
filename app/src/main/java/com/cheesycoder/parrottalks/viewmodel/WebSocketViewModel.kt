package com.cheesycoder.parrottalks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheesycoder.parrottalks.data.EchoWebSocket
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WebSocketViewModel(
    private val websocket: EchoWebSocket
) : ViewModel() {

    val messageLiveData = MutableLiveData<String>()

    fun start() = viewModelScope.launch {
        launch {
            websocket.onMessage.collect {
                messageLiveData.value = it.toString()
            }
        }
        launch { websocket.connect() }
    }

    fun stop() = viewModelScope.launch {
        websocket.disconnect()
    }

    fun send(inputText: String) = viewModelScope.launch {
        websocket.sendMessage(inputText)
    }
}