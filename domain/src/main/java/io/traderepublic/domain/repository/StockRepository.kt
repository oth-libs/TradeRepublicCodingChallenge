package io.traderepublic.domain.repository

import io.traderepublic.domain.model.StockModel
import io.traderepublic.domain.model.StockPriceModel
import kotlinx.coroutines.flow.Flow

interface StockRepository {

  fun observeStockUpdates(): Flow<StockPriceModel>

  fun subscribeStock(stockModel: StockModel)

  fun unsubscribeStock(stockModel: StockModel)
}