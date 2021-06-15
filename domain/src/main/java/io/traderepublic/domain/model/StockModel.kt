package io.traderepublic.domain.model

import java.math.BigDecimal

data class StockModel(
  val isin: String,
  val price: BigDecimal
)