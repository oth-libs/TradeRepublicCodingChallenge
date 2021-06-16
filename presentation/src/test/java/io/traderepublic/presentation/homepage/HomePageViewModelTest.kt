package io.traderepublic.presentation.homepage

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.traderepublic.domain.model.StockModel
import io.traderepublic.domain.model.StockPriceModel
import io.traderepublic.domain.model.factory.getHardcodedStocks
import io.traderepublic.domain.model.factory.getInitialHardcodedStockPrices
import io.traderepublic.domain.usecase.ObserveStockUpdatesUseCase
import io.traderepublic.domain.usecase.SubscribeToStockUseCase
import io.traderepublic.domain.usecase.UnsubscribeFromStockUseCase
import io.traderepublic.presentation.TestCoroutineRule
import io.traderepublic.presentation.getOrAwaitValue
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class HomePageViewModelTest {

  @get:Rule
  val rule = InstantTaskExecutorRule()

  @get:Rule
  val testCoroutineRule = TestCoroutineRule()

  private val observeStockUpdatesUseCase = mockk<ObserveStockUpdatesUseCase>()
  private val subscribeToStockUseCase = mockk<SubscribeToStockUseCase>()
  private val unsubscribeFromStockUseCase = mockk<UnsubscribeFromStockUseCase>()
  private val application = mockk<Application>(relaxed = true)

  private val viewModel = HomePageViewModel(observeStockUpdatesUseCase, subscribeToStockUseCase, unsubscribeFromStockUseCase, application)

  @Before
  fun setup() {
    coEvery { observeStockUpdatesUseCase() } returns flowOf(StockPriceModel(StockModel("US0378331005", "Apple"), BigDecimal.TEN))

    getInitialHardcodedStockPrices().forEach {
      coEvery { subscribeToStockUseCase(it.stock) } returns Unit
    }

    getInitialHardcodedStockPrices().forEach {
      coEvery { unsubscribeFromStockUseCase(it.stock) } returns Unit
    }
  }

  @Test
  fun testObserveStockUpdates() {

    viewModel.observeStockUpdates()

    // verify use case has been called
    coVerify(exactly = 1) { observeStockUpdatesUseCase() }

    // verify the stock price model has been updated
    val stockPriceModel = viewModel.stockModelData.find { it.stock.isin == "US0378331005" }
    assertNotNull(stockPriceModel)
    assertEquals(stockPriceModel.price, BigDecimal.TEN)

    // verify that live data was called
    assertEquals(viewModel.newDataAvailable.getOrAwaitValue(), Unit)
  }

  @Test
  fun testSubscribeToStocks() {
    viewModel.subscribeToStocks()

    // verify that all stocks have been subscribed to
    getHardcodedStocks().forEach {
      coVerify(exactly = 1) { subscribeToStockUseCase(it) }
    }
  }

}