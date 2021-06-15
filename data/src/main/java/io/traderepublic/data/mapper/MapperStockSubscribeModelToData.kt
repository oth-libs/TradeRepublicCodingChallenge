package io.traderepublic.data.mapper

import com.doublesymetrymusic.data.mapper.Mapper
import io.traderepublic.data.model.StockSubscribeData
import io.traderepublic.domain.model.StockSubscribeModel

/**
 * Map [StockSubscribeModel] to [StockSubscribeData]
 */
internal class MapperStockSubscribeModelToData : Mapper<StockSubscribeModel, StockSubscribeData> {

  override fun map(from: StockSubscribeModel): StockSubscribeData {
    return from.run {
      StockSubscribeData(isin = isin)
    }
  }
}