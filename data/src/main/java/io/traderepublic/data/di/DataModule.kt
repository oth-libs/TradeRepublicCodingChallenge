package io.traderepublic.data.di

import io.traderepublic.data.repository.StockRepositoryImp
import io.traderepublic.data.websocket.TRWebSocket
import io.traderepublic.data.websocket.TRWebSocketImp
import io.traderepublic.domain.repository.StockRepository
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DataModule {

  fun load() {
    loadKoinModules(module {
      //  web socket
      single<TRWebSocket> { TRWebSocketImp() }

      // repository
      single<StockRepository> { StockRepositoryImp(trWebSocket = get()) }
    })
  }
}