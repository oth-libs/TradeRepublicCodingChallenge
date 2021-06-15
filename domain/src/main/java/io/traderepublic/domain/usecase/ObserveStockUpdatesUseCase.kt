package io.traderepublic.domain.usecase

import io.traderepublic.domain.model.StockModel
import io.traderepublic.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow

class ObserveStockUpdatesUseCase(private val repository: StockRepository) {
  operator fun invoke(): Flow<StockModel> = repository.observeStockUpdates()
}
