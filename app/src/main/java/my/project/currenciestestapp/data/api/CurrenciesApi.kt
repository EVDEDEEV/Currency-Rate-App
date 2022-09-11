package my.project.currenciestestapp.data.api

import my.project.currenciestestapp.data.models.remote.CurrenciesResponse
import my.project.currenciestestapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrenciesApi {

    @GET("symbols")
    suspend fun getCurrencies(
        @Query("apikey") apiKey: String = Constants.API_KEY,
    ): Response<CurrenciesResponse>
}