package io.traderepublic.data.mapper

import com.doublesymetrymusic.data.mapper.Mapper
import io.traderepublic.data.model.StockUnsubscribeData
import io.traderepublic.domain.model.StockUnsubscribeModel

/**
 * Map [StockUnsubscribeModel] to [StockUnsubscribeData]
 */
internal class MapperStockUnsubscribeModelToData : Mapper<StockUnsubscribeModel, StockUnsubscribeData> {

  override fun map(from: StockUnsubscribeModel): StockUnsubscribeData {
    return from.run {
      StockUnsubscribeData(isin = isin)
    }
  }
}