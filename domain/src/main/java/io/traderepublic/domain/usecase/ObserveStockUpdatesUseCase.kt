package io.traderepublic.domain.usecase

import io.traderepublic.domain.model.StockPriceModel
import io.traderepublic.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow

class ObserveStockUpdatesUseCase(private val repository: StockRepository) {
  operator fun invoke(): Flow<StockPriceModel> = repository.observeStockUpdates()
}
