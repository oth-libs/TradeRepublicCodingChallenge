package io.traderepublic.domain.di

import io.traderepublic.domain.usecase.ObserveStockUpdatesUseCase
import io.traderepublic.domain.usecase.SubscribeToStockUseCase
import io.traderepublic.domain.usecase.UnsubscribeFromStockUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DomainModule {

  fun load() {
    loadKoinModules(module {
      single { ObserveStockUpdatesUseCase(repository = get()) }
      single { SubscribeToStockUseCase(repository = get()) }
      single { UnsubscribeFromStockUseCase(repository = get()) }
    })
  }
}