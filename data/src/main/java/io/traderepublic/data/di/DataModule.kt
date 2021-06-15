package io.traderepublic.data.di

import io.traderepublic.data.mapper.MapperStockDataToModel
import io.traderepublic.data.mapper.MapperStockSubscribeModelToData
import io.traderepublic.data.mapper.MapperStockUnsubscribeModelToData
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
      single { MapperStockDataToModel() }
      single { MapperStockSubscribeModelToData() }
      single { MapperStockUnsubscribeModelToData() }

      // repository
      single<StockRepository> {
        StockRepositoryImp(
          trWebSocket = get(),
          mapperStockDataToModel = get(),
          mapperStockSubscribeModelToData = get(),
          mapperStockUnsubscribeModelToData = get()
        )
      }
    })
  }
}