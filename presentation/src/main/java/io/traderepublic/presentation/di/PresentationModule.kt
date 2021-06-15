package io.traderepublic.presentation.di

import io.traderepublic.presentation.homepage.HomePageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object PresentationModule {

  fun load() {
    loadKoinModules(module {
      viewModel {
        HomePageViewModel(
          subscribeToStockUseCase = get(),
          unsubscribeFromStockUseCase = get(),
          observeStockUpdatesUseCase = get(),
          application = get()
        )
      }
    })
  }
}