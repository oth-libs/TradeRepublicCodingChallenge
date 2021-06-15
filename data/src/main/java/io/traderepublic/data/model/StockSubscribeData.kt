package io.traderepublic.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class StockSubscribeData(
  @SerialName("subscribe") val isin: String
)