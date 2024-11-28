package com.ibrahim.retrofit.network

data class StockResponse(
    val cotacaoAtual: String,
    val dividendos: List<Dividendo>
)

data class Dividendo(
    val dataPagamento: String,
    val valor : Double
)
