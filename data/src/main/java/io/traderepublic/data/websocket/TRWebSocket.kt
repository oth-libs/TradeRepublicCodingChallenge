package io.traderepublic.data.websocket

import io.traderepublic.domain.model.StockModel
import io.traderepublic.domain.model.StockSubscribeModel
import io.traderepublic.domain.model.StockUnsubscribeModel
import kotlinx.coroutines.flow.Flow

internal interface TRWebSocket {

  fun observeStockUpdates(): Flow<StockModel>
  fun subscribeStock(stockSubscribeModel: StockSubscribeModel)
  fun unsubscribeStock(stockUnsubscribeModel: StockUnsubscribeModel)

}