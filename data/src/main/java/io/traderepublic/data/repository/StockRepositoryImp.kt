package io.traderepublic.data.repository

import io.traderepublic.data.websocket.TRWebSocket
import io.traderepublic.domain.model.StockModel
import io.traderepublic.domain.model.StockSubscribeModel
import io.traderepublic.domain.model.StockUnsubscribeModel
import io.traderepublic.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow

internal class StockRepositoryImp(private val trWebSocket: TRWebSocket) : StockRepository {
  override fun observeStockUpdates(): Flow<StockModel> {
    return trWebSocket.observeStockUpdates()
  }

  override fun subscribeStock(stockSubscribeModel: StockSubscribeModel) {
    trWebSocket.subscribeStock(stockSubscribeModel)
  }

  override fun unsubscribeStock(stockUnsubscribeModel: StockUnsubscribeModel) {
    trWebSocket.unsubscribeStock(stockUnsubscribeModel)
  }

}