package io.traderepublic.presentation.homepage

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.viewModelScope
import io.traderepublic.domain.model.StockSubscribeModel
import io.traderepublic.domain.model.StockUnsubscribeModel
import io.traderepublic.domain.usecase.ObserveStockUpdatesUseCase
import io.traderepublic.domain.usecase.SubscribeToStockUseCase
import io.traderepublic.domain.usecase.UnsubscribeFromStockUseCase
import io.traderepublic.presentation.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomePageViewModel(
  private val observeStockUpdatesUseCase: ObserveStockUpdatesUseCase,
  private val subscribeToStockUseCase: SubscribeToStockUseCase,
  private val unsubscribeFromStockUseCase: UnsubscribeFromStockUseCase,
  application: Application
) : BaseViewModel(application) {

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // __      ___                 _      _           _____        _
  // \ \    / (_)               | |    (_)         |  __ \      | |
  //  \ \  / / _  _____      __ | |     ___   _____| |  | | __ _| |_ __ _
  //   \ \/ / | |/ _ \ \ /\ / / | |    | \ \ / / _ \ |  | |/ _` | __/ _` |
  //    \  /  | |  __/\ V  V /  | |____| |\ V /  __/ |__| | (_| | || (_| |
  //     \/   |_|\___| \_/\_/   |______|_| \_/ \___|_____/ \__,_|\__\__,_|
  //
  //Font Name: Big

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //  _____        _
  // |  __ \      | |
  // | |  | | __ _| |_ __ _
  // | |  | |/ _` | __/ _` |
  // | |__| | (_| | || (_| |
  // |_____/ \__,_|\__\__,_|
  //
  //Font Name: Big

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //  _    _           _____
  // | |  | |         / ____|
  // | |  | |___  ___| |     __ _ ___  ___
  // | |  | / __|/ _ \ |    / _` / __|/ _ \
  // | |__| \__ \  __/ |___| (_| \__ \  __/
  //  \____/|___/\___|\_____\__,_|___/\___|
  //
  //Font Name: Big
  /**
   * Start app with loading the first page
   */
//  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
//  private fun loadInitialPage() {
//    subscribeToStockUseCase(StockSubscribeModel(""))
//  }

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  fun ooo() {
    viewModelScope.launch {
      observeStockUpdatesUseCase().collect {
        println("stooooooock : $it")
      }
    }
  }

  fun aa() {
    subscribeToStockUseCase(StockSubscribeModel(""))
  }

  fun bb() {
    unsubscribeFromStockUseCase(StockUnsubscribeModel(""))
  }
}