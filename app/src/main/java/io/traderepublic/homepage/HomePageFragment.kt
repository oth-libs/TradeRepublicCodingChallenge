package io.traderepublic.homepage

import android.os.Bundle
import android.view.View
import io.traderepublic.BaseFragment
import io.traderepublic.R
import io.traderepublic.databinding.FragmentHomepageBinding
import io.traderepublic.domain.model.StockPriceModel
import io.traderepublic.extensions.setup
import io.traderepublic.presentation.homepage.HomePageViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
class HomePageFragment : BaseFragment<FragmentHomepageBinding, HomePageViewModel>(
  layoutId = R.layout.fragment_homepage,
) {

  private val stockPricesAdapter by lazy { StockPricesAdapter(viewModel.stockModelData, ::subscribeToStock, ::unsubscribeFromStock) }

  override fun setupBinding() {
    binding.viewModel = viewModel

    binding.rvStockPrices.setup()
    binding.rvStockPrices.adapter = stockPricesAdapter
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    observeViewModelCalls()
  }

  private fun observeViewModelCalls() {
//    viewModel.pageData.observe(viewLifecycleOwner, ::newPageDataReceived)
  }

  private fun subscribeToStock(stockPriceModel: StockPriceModel) {
    viewModel.subscribeToStock(stockPriceModel)
  }

  private fun unsubscribeFromStock(stockPriceModel: StockPriceModel) {
    viewModel.unsubscribeFromStock(stockPriceModel)
  }

}