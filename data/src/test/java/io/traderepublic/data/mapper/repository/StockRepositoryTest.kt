package io.traderepublic.data.mapper.repository

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.traderepublic.data.mapper.MapperStockDataToStockPriceModel
import io.traderepublic.data.mapper.MapperStockModelToStockSubscribeData
import io.traderepublic.data.mapper.MapperStockModelToStockUnsubscribeData
import io.traderepublic.data.model.StockData
import io.traderepublic.data.repository.StockRepositoryImp
import io.traderepublic.data.websocket.TRWebSocket
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

internal class StockRepositoryTest {

  private lateinit var trWebSocket: TRWebSocket
  private lateinit var repository: StockRepository

  @Before
  fun setUp() {
    trWebSocket = mockk(relaxed = true)

    repository = StockRepositoryImp(
      trWebSocket = trWebSocket,
      mapperStockDataToStockPriceModel = MapperStockDataToStockPriceModel(),
      mapperStockModelToStockSubscribeData = MapperStockModelToStockSubscribeData(),
      mapperStockModelToStockUnsubscribeData = MapperStockModelToStockUnsubscribeData()
    )
  }

  @Test
  fun testObserveStockUpdates() = runBlocking {
    val expected = StockPriceModel(StockModel("US0378331005", "Apple"), BigDecimal.ONE)

    every { repository.observeStockUpdates() } returns flowOf(StockPriceModel(StockModel("US0378331005", "Apple"), BigDecimal.ONE))
    every { trWebSocket.observeStockUpdates() } returns flowOf(StockData("US0378331005", BigDecimal.ONE))

    val result = repository.observeStockUpdates().first()

    assertEquals(expected, result)
  }

  @Test
  fun testSubscribeStock() {
    val stockModel = StockModel("US0378331005", "Apple")

    repository.subscribeStock(stockModel)

    verify(exactly = 1) { trWebSocket.subscribeStock(MapperStockModelToStockSubscribeData().map(stockModel)) }
  }

  @Test
  fun testUnsubscribeStock() {
    val stockModel = StockModel("US0378331005", "Apple")

    repository.unsubscribeStock(stockModel)

    verify(exactly = 1) { trWebSocket.unsubscribeStock(MapperStockModelToStockUnsubscribeData().map(stockModel)) }
  }
}