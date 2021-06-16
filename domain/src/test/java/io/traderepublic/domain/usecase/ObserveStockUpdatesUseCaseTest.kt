package io.traderepublic.domain.usecase

import io.mockk.coEvery
import io.mockk.mockk
import io.traderepublic.domain.model.StockModel
import io.traderepublic.domain.model.StockPriceModel
import io.traderepublic.domain.repository.StockRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class ObserveStockUpdatesUseCaseTest {
  private val repository: StockRepository = mockk()

  @Test
  fun testObserveStockUpdatesUseCase() = runBlocking {
    val stockPriceModel = StockPriceModel(StockModel("US0378331005", "Apple"), BigDecimal.TEN)

    coEvery { repository.observeStockUpdates() } returns flowOf(stockPriceModel)

    val useCase = ObserveStockUpdatesUseCase(repository = repository)

    val result = useCase().first()

    assertEquals(stockPriceModel, result)
  }
}
