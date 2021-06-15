package io.traderepublic.homepage

import android.os.Bundle
import android.view.View
import io.traderepublic.BaseFragment
import io.traderepublic.R
import io.traderepublic.databinding.FragmentHomepageBinding
import io.traderepublic.presentation.homepage.HomePageViewModel
import kotlinx.android.synthetic.main.fragment_homepage.sstart
import kotlinx.android.synthetic.main.fragment_homepage.sstop
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
class HomePageFragment : BaseFragment<FragmentHomepageBinding, HomePageViewModel>(
  layoutId = R.layout.fragment_homepage,
) {

  override fun setupBinding() {
    binding.viewModel = viewModel

    setupViews()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    observeViewModelCalls()

    sstart.setOnClickListener {
      viewModel.aa()
    }

    sstop.setOnClickListener {
      viewModel.bb()
    }
  }

  private fun setupViews() {


  }

  private fun observeViewModelCalls() {
//    viewModel.pageData.observe(viewLifecycleOwner, ::newPageDataReceived)
  }

}