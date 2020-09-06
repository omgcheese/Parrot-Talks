package com.cheesycoder.parrottalks.data

import com.cheesycoder.parrottalks.model.ConnectionStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class EchoWebSocketImplTest {

    private var websocket: EchoWebSocketImpl? = null
    private val websocketURL = "Test web scoket url"
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        websocket = EchoWebSocketImpl(websocketURL, testCoroutineDispatcher)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun `connect - successful verify status`() = testCoroutineDispatcher.runBlockingTest {
        givenInitialStatusIsIdleAndStart()

        // TODO: Establish Connection

        Assert.assertEquals(websocket!!.status, ConnectionStatus.CONNECTED)
    }

    @Test
    fun `connect - error verify status`() = testCoroutineDispatcher.runBlockingTest {
        givenInitialStatusIsIdleAndStart()

        // TODO: Establish Connection

        Assert.assertEquals(websocket!!.status, ConnectionStatus.IDLE)
    }

    @Test
    fun `disconnect - verify status`() = testCoroutineDispatcher.runBlockingTest {
        givenInitialStatusIsIdleAndStart()

        // TODO: Establish Connection

        Assert.assertEquals(websocket!!.status, ConnectionStatus.IDLE)
    }

    @Test
    fun `sendMessage - verify status`() = testCoroutineDispatcher.runBlockingTest {
        givenInitialStatusIsIdleAndStart()

        // TODO: Establish Connection

        Assert.assertEquals(websocket!!.status, ConnectionStatus.IDLE)
    }

    private suspend fun givenInitialStatusIsIdleAndStart() {
        Assert.assertEquals(websocket!!.status, ConnectionStatus.IDLE)
        websocket!!.connect()
        Assert.assertEquals(websocket!!.status, ConnectionStatus.IS_CONNECTING)
    }

    @After
    fun tearDown() {
        websocket = null
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}