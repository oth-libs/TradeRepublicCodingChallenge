package io.traderepublic.domain.model

import io.traderepublic.domain.utils.Utils
import java.math.BigDecimal

data class StockPriceModel(
  val stock: StockModel,
  val price: BigDecimal? = null
) {
  fun getPriceFormatted(): String {
    return if (price == null) "--"
    else Utils.formatAmountWithCurrency(price)
  }
}

data class StockModel(
  val isin: String,
  val name: String
)