package io.traderepublic.data.mapper

import io.traderepublic.data.model.StockData
import io.traderepublic.domain.model.StockPriceModel
import io.traderepublic.domain.model.factory.getHardcodedStocks

/**
 * Map [StockData] to [StockPriceModel]
 */
internal class MapperStockDataToStockPriceModel : Mapper<StockData, StockPriceModel> {

  override fun map(from: StockData): StockPriceModel {
    return from.run {
      StockPriceModel(
        stock = getHardcodedStocks().first { it.isin == from.isin },
        price = price
      )
    }
  }
}