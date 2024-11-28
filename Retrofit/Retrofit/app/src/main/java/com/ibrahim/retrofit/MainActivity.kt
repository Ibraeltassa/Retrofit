package com.ibrahim.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.ibrahim.retrofit.network.RetrofitClient
import com.ibrahim.retrofit.network.StockApiService
import com.ibrahim.retrofit.network.StockResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textView)
        val service = RetrofitClient.instance.create(StockApiService::class.java)


        val codigoAcao = "VALE3"

        // Fazendo a requisição
        val call = service.getStockDetails(codigoAcao)
        call.enqueue(object : Callback<StockResponse> {



            override fun onResponse(call: Call<StockResponse>, response: Response<StockResponse>) {
                if (response.isSuccessful) {
                    // Logando o corpo da resposta como string para verificar o que está sendo recebido
                    val responseBody = response.body()?.toString() ?: response.errorBody()?.string()
                    Log.d("RetrofitResponse", "Resposta da API: $responseBody")

                    val stockDetails = response.body()
                    textView.text = "Cotação atual: ${stockDetails?.cotacaoAtual}\n"

                    stockDetails?.dividendos?.forEach {
                        textView.append("Data: ${it.dataPagamento}, Valor: ${it.valor}\n")
                    }
                } else {
                    textView.text = "Erro na resposta: ${response.code()}"
                }
            }


            override fun onFailure(call: Call<StockResponse>, t: Throwable) {
                textView.text = "Erro na requisição: ${t.message}"
            }
        })
    }
}
