package io.traderepublic.data.websocket

import io.traderepublic.data.model.StockData
import io.traderepublic.data.model.StockSubscribeData
import io.traderepublic.data.model.StockUnsubscribeData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.math.BigDecimal

@ExperimentalCoroutinesApi
internal class TRWebSocketImp : TRWebSocket {

  private val client = OkHttpClient()
  private lateinit var webSocket: WebSocket

  private val stockUpdatesFlow: Flow<StockData> = callbackFlow {
    val webSocketListener = object : WebSocketListener() {
      override fun onMessage(webSocket: WebSocket, text: String) {
        trySend(StockData(text, BigDecimal.ONE))
      }
    }

    val request: Request = Request.Builder().url("ws://159.89.15.214:8080/").build() // todo
    webSocket = client.newWebSocket(request, webSocketListener)
    client.dispatcher.executorService.shutdown()

    awaitClose { webSocket.close(1000, null) }
  }

  override fun observeStockUpdates(): Flow<StockData> = stockUpdatesFlow

  override fun subscribeStock(stockSubscribe: StockSubscribeData) {
    webSocket.send(Json.encodeToString(stockSubscribe))
  }

  override fun unsubscribeStock(stockUnsubscribe: StockUnsubscribeData) {
    webSocket.send(Json.encodeToString(stockUnsubscribe))
  }
}