package com.cheesycoder.parrottalks.data

import com.cheesycoder.parrottalks.model.ConnectionStatus
import com.cheesycoder.parrottalks.model.NewMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import okhttp3.*
import kotlin.coroutines.resume

class EchoWebSocketImpl(
    private val websocketUrl: String,
    private val factory: OkHttpClientFactory,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : EchoWebSocket {

    private var _status = ConnectionStatus.IDLE
    private val messageRelay = BroadcastChannel<NewMessage>(1)
    private var _websocket: WebSocket? = null

    override val status: ConnectionStatus
        get() = _status
    override val onMessage: Flow<NewMessage>
        get() = messageRelay.asFlow()

    override suspend fun connect() = withContext(coroutineDispatcher) {
        _status = ConnectionStatus.IS_CONNECTING
        val request = Request.Builder().url(websocketUrl).build()
        val okHttpClient = factory.create()
        _websocket = suspendCancellableCoroutine<WebSocket> {
            okHttpClient.newWebSocket(request, object : WebSocketListener() {
                override fun onOpen(webSocket: WebSocket, response: Response) {
                    _status = ConnectionStatus.CONNECTED
                    it.resume(webSocket)
                }

                override fun onMessage(webSocket: WebSocket, text: String) {
                    messageRelay.offer(NewMessage(id = 100, content = text))
                }

                override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                    _websocket?.close(1000, null)
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    _status = ConnectionStatus.IDLE
                    messageRelay.offer(NewMessage(id = 100, content = "Error"))
                }

                override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                    _status = ConnectionStatus.IDLE
                }
            })
        }
        okHttpClient.dispatcher.executorService.shutdown()
    }

    override suspend fun disconnect(): Unit = withContext(coroutineDispatcher) {
        _websocket?.close(1000, "Closing manually")
    }

    override suspend fun sendMessage(message: String): Unit = withContext(coroutineDispatcher) {
        _websocket?.send(message)
    }
}
