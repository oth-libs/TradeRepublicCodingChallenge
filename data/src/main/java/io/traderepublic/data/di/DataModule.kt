package io.traderepublic.data.di

import io.traderepublic.data.mapper.MapperStockDataToStockPriceModel
import io.traderepublic.data.mapper.MapperStockModelToStockSubscribeData
import io.traderepublic.data.mapper.MapperStockModelToStockUnsubscribeData
import io.traderepublic.data.repository.StockRepositoryImp
import io.traderepublic.data.websocket.TRWebSocket
import io.traderepublic.data.websocket.TRWebSocketImp
import io.traderepublic.domain.repository.StockRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DataModule {

  @ExperimentalCoroutinesApi fun load() {
    loadKoinModules(module {
      //  web socket
      single<TRWebSocket> { TRWebSocketImp() }

      // mappers
      single { MapperStockDataToStockPriceModel() }
      single { MapperStockModelToStockSubscribeData() }
      single { MapperStockModelToStockUnsubscribeData() }

      // repository
      single<StockRepository> {
        StockRepositoryImp(
          trWebSocket = get(),
          mapperStockDataToStockPriceModel = get(),
          mapperStockModelToStockSubscribeData = get(),
          mapperStockModelToStockUnsubscribeData = get()
        )
      }
    })
  }
}