package io.traderepublic.data.mapper

import io.traderepublic.data.model.StockSubscribeData
import io.traderepublic.data.model.StockUnsubscribeData
import io.traderepublic.domain.model.StockModel
import org.junit.Test
import kotlin.test.assertEquals

class MapperStockModelToStockSubscribeDataTest {

  private val mapper: Mapper<StockModel, StockSubscribeData> = MapperStockModelToStockSubscribeData()

  @Test
  fun testMapper() {
    val expected = StockSubscribeData("ISIN123")

    val result = mapper.map(StockModel("ISIN123", "NAME123"))

    assertEquals(expected, result)
  }

}