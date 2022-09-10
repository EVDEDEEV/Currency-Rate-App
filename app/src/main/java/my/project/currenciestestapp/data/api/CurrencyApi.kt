package my.project.currenciestestapp.data.api

import my.project.currenciestestapp.BuildConfig
import my.project.currenciestestapp.data.models.remote.RatesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface CurrencyApi {

    @GET("latest")
    suspend fun getCurrencies(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("base") base: String = "USD",
    ): Response<RatesResponse>

    companion object {
        const val API_KEY = BuildConfig.API_KEY
    }
}


