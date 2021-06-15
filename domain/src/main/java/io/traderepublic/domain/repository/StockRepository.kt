package io.traderepublic.domain.repository

import io.traderepublic.domain.model.StockModel
import io.traderepublic.domain.model.StockSubscribeModel
import io.traderepublic.domain.model.StockUnsubscribeModel
import kotlinx.coroutines.flow.Flow

interface StockRepository {

  fun observeStockUpdates(): Flow<StockModel>

  fun subscribeStock(stockSubscribeModel: StockSubscribeModel)

  fun unsubscribeStock(stockUnsubscribeModel: StockUnsubscribeModel)
}