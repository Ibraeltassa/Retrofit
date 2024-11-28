package com.ibrahim.retrofit.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StockApiService {
    @GET("api/cotacoes/acao/chart/{codigoAcao}/365/true/real")
    fun getStockDetails(@Path("codigoAcao") codigoAcao: String): Call<StockResponse>
}
