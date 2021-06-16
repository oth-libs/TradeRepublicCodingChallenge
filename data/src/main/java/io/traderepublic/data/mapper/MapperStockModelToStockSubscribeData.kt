package io.traderepublic.data.mapper

import io.traderepublic.data.model.StockSubscribeData
import io.traderepublic.domain.model.StockModel

/**
 * Map [StockModel] to [StockSubscribeData]
 */
internal class MapperStockModelToStockSubscribeData : Mapper<StockModel, StockSubscribeData> {

  override fun map(from: StockModel): StockSubscribeData {
    return from.run {
      StockSubscribeData(isin = isin)
    }
  }
}