package io.traderepublic.domain.usecase

import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import io.traderepublic.domain.model.StockModel
import io.traderepublic.domain.repository.StockRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UnsubscribeToStockUseCaseTest {
  private val repository: StockRepository = mockk()

  @Test
  fun testUnsubscribeFromStockUseCase() = runBlocking {
    val stockModel = StockModel("US0378331005", "Apple")

    coEvery { repository.unsubscribeStock(stockModel) } returns Unit

    val useCase = UnsubscribeFromStockUseCase(repository = repository)
    useCase(stockModel)

    verify(exactly = 1) { repository.unsubscribeStock(stockModel) }
  }
}
