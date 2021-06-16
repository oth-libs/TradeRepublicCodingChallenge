package io.traderepublic.domain.utils

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

object Utils {
  private const val CURRENCY_SYMBOL = "€"

  /**
   * format an amount to 2 fraction digits and appends a currency symbol
   */
  fun formatAmountWithCurrency(amount: BigDecimal): String {
    val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
    (format as? DecimalFormat)?.apply {

      val dfs: DecimalFormatSymbols = DecimalFormat().decimalFormatSymbols
      dfs.currencySymbol = CURRENCY_SYMBOL
      decimalFormatSymbols = dfs

      maximumFractionDigits = if (amount.compareTo(BigDecimal.ZERO) == 0) 0 else 2
      minimumFractionDigits = if (amount.compareTo(BigDecimal.ZERO) == 0) 0 else 2
    }

    return format.format(amount)
  }

}