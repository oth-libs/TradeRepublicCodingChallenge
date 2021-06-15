package io.traderepublic.data.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.math.BigDecimal

@Serializable
internal data class StockData(
  @SerialName("isin") val isin: String,
  @SerialName("price") @Serializable(with = BigDecimalSerializer::class) val price: BigDecimal
)

object BigDecimalSerializer : KSerializer<BigDecimal> {
  override val descriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

  override fun deserialize(decoder: Decoder): BigDecimal {
    return (decoder.decodeString()).toBigDecimal()
  }

  override fun serialize(encoder: Encoder, value: BigDecimal) {
    encoder.encodeString(value.toString())
  }
}
