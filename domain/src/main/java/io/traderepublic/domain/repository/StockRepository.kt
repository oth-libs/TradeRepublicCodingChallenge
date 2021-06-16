package io.traderepublic.domain.repository

import io.traderepublic.domain.model.StockModel
import io.traderepublic.domain.model.StockPriceModel
import kotlinx.coroutines.flow.Flow

interface StockRepository {

  /**
   * returns a flow of price udpates
   */
  fun observeStockUpdates(): Flow<StockPriceModel>

  /**
   * send a subscribe request
   */
  fun subscribeStock(stockModel: StockModel)

  /**
   * send an unsubscribe request
   */
  fun unsubscribeStock(stockModel: StockModel)
}