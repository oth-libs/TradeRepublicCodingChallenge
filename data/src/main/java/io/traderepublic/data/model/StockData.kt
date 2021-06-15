package io.traderepublic.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

internal data class StockData(
   val isin: String,
   val price: BigDecimal
)