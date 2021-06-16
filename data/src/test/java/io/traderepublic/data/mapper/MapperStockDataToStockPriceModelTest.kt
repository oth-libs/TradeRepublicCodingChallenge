package io.traderepublic.data.mapper

import io.traderepublic.data.model.StockData
import io.traderepublic.domain.model.StockModel
import io.traderepublic.domain.model.StockPriceModel
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class MapperStockDataToStockPriceModelTest {

  private val mapper: Mapper<StockData, StockPriceModel> = MapperStockDataToStockPriceModel()

  @Test
  fun testMapper() {
    val expected = StockPriceModel(StockModel("US0378331005", "Apple"), BigDecimal.TEN)

    val result = mapper.map(StockData("US0378331005", BigDecimal.TEN))

    assertEquals(expected, result)
  }

}