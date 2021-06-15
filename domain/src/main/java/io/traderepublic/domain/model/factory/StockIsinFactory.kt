package io.traderepublic.domain.model.factory

import io.traderepublic.domain.model.StockModel
import io.traderepublic.domain.model.StockPriceModel

/**
 * returns a hardcoded list of supported stocks
 */
fun getHardcodedStocks() = listOf(
  StockModel("US0378331005", "Apple"),
  StockModel("US5949181045", "Miscrosoft"),
  StockModel("US88160R1014", "Tesla"),
  StockModel("NET000N26000", "N26"),
)

/**
 * returns an initial list for the adapter with no prices
 */
fun getInitialHardcodedStockPrices() = getHardcodedStocks().map { StockPriceModel(it) }