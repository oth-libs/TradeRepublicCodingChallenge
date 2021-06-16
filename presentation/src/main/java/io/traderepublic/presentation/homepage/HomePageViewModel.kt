package io.traderepublic.presentation.homepage

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.viewModelScope
import io.traderepublic.domain.model.StockPriceModel
import io.traderepublic.domain.model.factory.getInitialHardcodedStockPrices
import io.traderepublic.domain.usecase.ObserveStockUpdatesUseCase
import io.traderepublic.domain.usecase.SubscribeToStockUseCase
import io.traderepublic.domain.usecase.UnsubscribeFromStockUseCase
import io.traderepublic.presentation.BaseViewModel
import io.traderepublic.presentation.livedata.SingleLiveEvent
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
  private val _newDataAvailable = SingleLiveEvent<Unit>()
  val newDataAvailable: LiveData<Unit> = _newDataAvailable

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
  private val _stockModelData = mutableListOf<StockPriceModel>().apply { addAll(getInitialHardcodedStockPrices()) }
  val stockModelData: List<StockPriceModel> = _stockModelData

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
   * Start app with observing stock prices
   */
  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  @Suppress("UNUSED") fun observeStockUpdates() {
    viewModelScope.launch {
      observeStockUpdatesUseCase().collect { stockPriceModel ->
        _stockModelData.apply {
          set(indexOfFirst { it.stock == stockPriceModel.stock }, stockPriceModel)
        }

        _newDataAvailable.value = Unit
      }
    }

    subscribeToStocks()
  }

  fun subscribeToStocks() {
    _stockModelData.forEach { subscribeToStockUseCase(it.stock) }
  }

  /**
   * this method is called on [Lifecycle.Event.ON_STOP] and closing the WebSocket automatically when the coroutine scope is stopped using awaitClose in
   * io.traderepublic.data.websocket.TRWebSocketImp#stockUpdatesFlow#awaitClose
   */
  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  fun unsubscribeFromStocks() {
    _stockModelData.forEach { unsubscribeFromStockUseCase(it.stock) }
  }
}