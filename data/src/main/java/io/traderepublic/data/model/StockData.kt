package io.traderepublic.data.model

import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
internal data class StockData(
  val isin: String,
  val price: BigDecimal
)