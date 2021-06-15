package io.traderepublic.data.mapper

import com.doublesymetrymusic.data.mapper.Mapper
import io.traderepublic.data.model.StockData
import io.traderepublic.domain.model.StockModel

/**
 * Map [StockData] to [StockModel]
 */
internal class MapperStockDataToModel : Mapper<StockData, StockModel> {

  override fun map(from: StockData): StockModel {
    return from.run {
      StockModel(isin = isin, price = price)
    }
  }
}