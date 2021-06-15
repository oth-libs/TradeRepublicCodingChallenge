package io.traderepublic.data.websocket

import io.traderepublic.domain.model.StockModel
import io.traderepublic.domain.model.StockSubscribeModel
import io.traderepublic.domain.model.StockUnsubscribeModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.math.BigDecimal

@ExperimentalCoroutinesApi
internal class TRWebSocketImp : TRWebSocket {

  private val client = OkHttpClient()
  private lateinit var webSocket: WebSocket

  private val stockUpdatesFlow: Flow<StockModel> = callbackFlow {
    val webSocketListener = object : WebSocketListener() {
      override fun onMessage(webSocket: WebSocket, text: String) {
        trySend(StockModel(text, BigDecimal.ONE))
      }
    }

    val request: Request = Request.Builder().url("ws://159.89.15.214:8080/").build() // todo
    webSocket = client.newWebSocket(request, webSocketListener)
    client.dispatcher.executorService.shutdown()

    awaitClose { webSocket.close(1000, null) }
  }

  override fun observeStockUpdates(): Flow<StockModel> = stockUpdatesFlow

  override fun subscribeStock(stockSubscribeModel: StockSubscribeModel) {
    webSocket.send("""{"subscribe":"US0378331005"}""".trim())//TODO
  }

  override fun unsubscribeStock(stockUnsubscribeModel: StockUnsubscribeModel) {
    webSocket.send("""{"unsubscribe":"US0378331005"}""".trim())//TODO
  }
}