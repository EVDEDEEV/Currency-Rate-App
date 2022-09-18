package my.project.currenciestestapp.data.api

import my.project.currenciestestapp.data.models.remote.CurNameResponse
import my.project.currenciestestapp.data.models.remote.CurrencyName
import my.project.currenciestestapp.data.models.remote.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query


interface CurrencyApi {

    @GET("latest")
    suspend fun getCurrencyRates(
        @Query("base") baseCurrency: String,
        @Query("amount") amount: String
    ): Response<CurrencyResponse>

    @GET("symbols")
    suspend fun getCurrencies(): Response<CurrencyName>
}