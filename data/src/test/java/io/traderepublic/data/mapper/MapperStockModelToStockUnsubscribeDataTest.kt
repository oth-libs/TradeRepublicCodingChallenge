package io.traderepublic.data.mapper

import io.traderepublic.data.model.StockUnsubscribeData
import io.traderepublic.domain.model.StockModel
import org.junit.Test
import kotlin.test.assertEquals

class MapperStockModelToStockUnsubscribeDataTest {

  private val mapper: Mapper<StockModel, StockUnsubscribeData> = MapperStockModelToStockUnsubscribeData()

  @Test
  fun testMapper() {
    val expected = StockUnsubscribeData("ISIN123")

    val result = mapper.map(StockModel("ISIN123", "NAME123"))

    assertEquals(expected, result)
  }

}