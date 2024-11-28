package com.ibrahim.retrofit.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://investidor10.com.br/"

    val instance: Retrofit by lazy {
        // Configurando o logging para ver as requisições e respostas
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)  // Loga o corpo completo das respostas

        // Configurando o OkHttpClient com o logging
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        // Configurando o Gson para ser mais permissivo com JSON malformado
        val gson = GsonBuilder()
            .setLenient()
            .create()

        // Construindo o Retrofit com o cliente HTTP e Gson configurado
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))  // Usando Gson com setLenient
            .client(httpClient.build())  // Incluindo o cliente com o interceptor de logging
            .build()
    }
}
