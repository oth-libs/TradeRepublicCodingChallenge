package io.traderepublic.data.mapper

internal interface Mapper<FROM, TO> {
  fun map(from: FROM): TO
}