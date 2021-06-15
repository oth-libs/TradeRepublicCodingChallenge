package io.traderepublic.data.repository

import io.traderepublic.data.mapper.MapperStockDataToModel
import io.traderepublic.data.mapper.MapperStockSubscribeModelToData
import io.traderepublic.data.mapper.MapperStockUnsubscribeModelToData
import io.traderepublic.data.websocket.TRWebSocket
import io.traderepublic.domain.model.StockModel
import io.traderepublic.domain.model.StockSubscribeModel
import io.traderepublic.domain.model.StockUnsubscribeModel
import io.traderepublic.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class StockRepositoryImp(
  private val trWebSocket: TRWebSocket,
  private val mapperStockDataToModel: MapperStockDataToModel,
  private val mapperStockSubscribeModelToData: MapperStockSubscribeModelToData,
  private val mapperStockUnsubscribeModelToData: MapperStockUnsubscribeModelToData
) : StockRepository {
  override fun observeStockUpdates(): Flow<StockModel> {
    return trWebSocket.observeStockUpdates().map { mapperStockDataToModel.map(it) }
  }

  override fun subscribeStock(stockSubscribeModel: StockSubscribeModel) {
    trWebSocket.subscribeStock(mapperStockSubscribeModelToData.map(stockSubscribeModel))
  }

  override fun unsubscribeStock(stockUnsubscribeModel: StockUnsubscribeModel) {
    trWebSocket.unsubscribeStock(mapperStockUnsubscribeModelToData.map(stockUnsubscribeModel))
  }

}