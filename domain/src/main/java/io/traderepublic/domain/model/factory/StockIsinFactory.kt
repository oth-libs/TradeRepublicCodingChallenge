package io.traderepublic.domain.model.factory

import io.traderepublic.domain.model.StockModel
import io.traderepublic.domain.model.StockPriceModel

/**
 * returns a hardcoded list of supported stocks
 */
fun getHardcodedStocks() = listOf(
  StockModel("US0378331005", "Apple"),
  StockModel("US5949181045", "Microsoft"),
  StockModel("US88160R1014", "Tesla"),
  StockModel("NET000N26000", "N26"),
  StockModel("US02079K3059", "Google"),
  StockModel("KR7005930003", "Samsung"),
  StockModel("KYG9830T1067", "Xiaomi"),
  StockModel("KYG7397A1067", "Razer"),
  StockModel("US4128221086", "Harley Davidson"),
  StockModel("US24703L2025", "Dell"),
)

/**
 * returns an initial list for the adapter with no prices
 */
fun getInitialHardcodedStockPrices() = getHardcodedStocks().map { StockPriceModel(it) }