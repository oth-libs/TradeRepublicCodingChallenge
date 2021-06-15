package io.traderepublic.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.traderepublic.databinding.ItemViewStockPriceBinding
import io.traderepublic.domain.model.StockPriceModel

class StockPricesAdapter(
  private val data: List<StockPriceModel>,
  private val subscribeToStock: (StockPriceModel) -> Unit,
  private val unsubscribeFromStock: (StockPriceModel) -> Unit
) : RecyclerView.Adapter<StockPricesAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
    return ViewHolder(ItemViewStockPriceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
  }

  override fun getItemCount() = data.size

  private fun getItem(position: Int) = data[position]

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.apply {
      bind(getItem(position))
    }
  }

  inner class ViewHolder(private val binding: ItemViewStockPriceBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(stockPriceModel: StockPriceModel) {
      binding.apply {
        this.stockModel = stockPriceModel
        executePendingBindings()
      }
    }
  }
}

