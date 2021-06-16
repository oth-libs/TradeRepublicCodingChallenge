package io.traderepublic.data.mapper

import io.traderepublic.data.model.StockUnsubscribeData
import io.traderepublic.domain.model.StockModel

/**
 * Map [StockModel] to [StockUnsubscribeData]
 */
internal class MapperStockModelToStockUnsubscribeData : Mapper<StockModel, StockUnsubscribeData> {

  override fun map(from: StockModel): StockUnsubscribeData {
    return from.run {
      StockUnsubscribeData(isin = isin)
    }
  }
}