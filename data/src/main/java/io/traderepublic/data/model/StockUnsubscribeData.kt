package io.traderepublic.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class StockUnsubscribeData(
  @SerialName("unsubscribe") val isin: String
)