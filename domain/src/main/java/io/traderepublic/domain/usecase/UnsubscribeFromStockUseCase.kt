package io.traderepublic.domain.usecase

import io.traderepublic.domain.model.StockModel
import io.traderepublic.domain.repository.StockRepository

class UnsubscribeFromStockUseCase(private val repository: StockRepository) {
  operator fun invoke(stockModel: StockModel): Unit = run { repository.unsubscribeStock(stockModel) }
}
