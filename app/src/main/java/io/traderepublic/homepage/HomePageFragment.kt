package io.traderepublic.homepage

import android.os.Bundle
import android.view.View
import io.traderepublic.BaseFragment
import io.traderepublic.R
import io.traderepublic.databinding.FragmentHomepageBinding
import io.traderepublic.extensions.setup
import io.traderepublic.presentation.homepage.HomePageViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
class HomePageFragment : BaseFragment<FragmentHomepageBinding, HomePageViewModel>(
  layoutId = R.layout.fragment_homepage,
) {

  private val stockPricesAdapter by lazy { StockPricesAdapter(viewModel.stockModelData) }

  override fun setupBinding() {
    binding.viewModel = viewModel

    binding.rvStockPrices.apply {
      setup()
      adapter = stockPricesAdapter
    }

    binding.btnSubscribe.setOnClickListener { subscribeToStocks() }
    binding.btnUnsubscribe.setOnClickListener { unsubscribeFromStocks() }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    observeViewModelCalls()
  }

  private fun observeViewModelCalls() {
    viewModel.newDataAvailable.observe(viewLifecycleOwner, ::onNewDataAvailable)
  }

  /**
   * notify the adapter
   */
  private fun onNewDataAvailable(@Suppress("UNUSED_PARAMETER") unit: Unit) {
    stockPricesAdapter.notifyDataSetChanged()
  }

  private fun subscribeToStocks() {
    viewModel.subscribeToStocks()
  }

  private fun unsubscribeFromStocks() {
    viewModel.unsubscribeFromStocks()
  }
}