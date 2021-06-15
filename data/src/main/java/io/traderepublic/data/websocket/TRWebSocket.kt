package io.traderepublic.data.websocket

import io.traderepublic.data.model.StockData
import io.traderepublic.data.model.StockSubscribeData
import io.traderepublic.data.model.StockUnsubscribeData
import kotlinx.coroutines.flow.Flow

internal interface TRWebSocket {

  fun observeStockUpdates(): Flow<StockData>
  fun subscribeStock(stockSubscribe: StockSubscribeData)
  fun unsubscribeStock(stockUnsubscribe: StockUnsubscribeData)

}