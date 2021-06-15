package io.traderepublic.domain.usecase

import io.traderepublic.domain.model.StockSubscribeModel
import io.traderepublic.domain.repository.StockRepository

class SubscribeToStockUseCase(private val repository: StockRepository) {
  operator fun invoke(stockSubscribeModel: StockSubscribeModel): Unit = run { repository.subscribeStock(stockSubscribeModel) }
}
