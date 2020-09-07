package com.cheesycoder.parrottalks.data

import com.cheesycoder.parrottalks.model.ConnectionStatus
import com.cheesycoder.parrottalks.model.NewMessage
import kotlinx.coroutines.flow.Flow

interface EchoWebSocket{
    val status: ConnectionStatus
    val onMessage: Flow<NewMessage>
    suspend fun connect()
    suspend fun disconnect()
    suspend fun sendMessage(message: String)
}
