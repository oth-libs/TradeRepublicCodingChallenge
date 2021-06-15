package io.traderepublic.data.repository

import io.traderepublic.data.mapper.MapperStockDataToStockPriceModel
import io.traderepublic.data.mapper.MapperStockModelToStockSubscribeData
import io.traderepublic.data.mapper.MapperStockModelToStockUnsubscribeData
import io.traderepublic.data.websocket.TRWebSocket
import io.traderepublic.domain.model.StockModel
import io.traderepublic.domain.model.StockPriceModel
import io.traderepublic.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class StockRepositoryImp(
  private val trWebSocket: TRWebSocket,
  private val mapperStockDataToStockPriceModel: MapperStockDataToStockPriceModel,
  private val mapperStockModelToStockSubscribeData: MapperStockModelToStockSubscribeData,
  private val mapperStockModelToStockUnsubscribeData: MapperStockModelToStockUnsubscribeData
) : StockRepository {
  override fun observeStockUpdates(): Flow<StockPriceModel> {
    return trWebSocket.observeStockUpdates().map { mapperStockDataToStockPriceModel.map(it) }
  }

  override fun subscribeStock(stockModel: StockModel) {
    trWebSocket.subscribeStock(mapperStockModelToStockSubscribeData.map(stockModel))
  }

  override fun unsubscribeStock(stockModel: StockModel) {
    trWebSocket.unsubscribeStock(mapperStockModelToStockUnsubscribeData.map(stockModel))
  }

}