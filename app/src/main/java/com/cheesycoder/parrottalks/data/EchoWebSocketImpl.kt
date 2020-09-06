package com.cheesycoder.parrottalks.data

import com.cheesycoder.parrottalks.model.ConnectionStatus
import com.cheesycoder.parrottalks.model.NewMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class EchoWebSocketImpl(
    private val websocketUrl: String,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : EchoWebSocket {
    override val status: ConnectionStatus
        get() = TODO("Not yet implemented")
    override val onMessage: Flow<NewMessage>
        get() = TODO("Not yet implemented")

    override suspend fun connect() = withContext(coroutineDispatcher) {
        TODO("Not yet implemented")
    }

    override suspend fun disconnect() = withContext(coroutineDispatcher) {
        TODO("Not yet implemented")
    }

    override suspend fun sendMessage(message: NewMessage) = withContext(coroutineDispatcher) {
        TODO("Not yet implemented")
    }
}
