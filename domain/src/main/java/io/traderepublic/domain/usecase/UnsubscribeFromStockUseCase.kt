package io.traderepublic.domain.usecase

import io.traderepublic.domain.model.StockUnsubscribeModel
import io.traderepublic.domain.repository.StockRepository

class UnsubscribeFromStockUseCase(private val repository: StockRepository) {
  operator fun invoke(stockUnsubscribeModel: StockUnsubscribeModel): Unit = run { repository.unsubscribeStock(stockUnsubscribeModel) }
}
