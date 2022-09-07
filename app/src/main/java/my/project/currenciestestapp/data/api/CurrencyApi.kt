package my.project.currenciestestapp.data.api

import my.project.currenciestestapp.data.models.local.CurrencyItemEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface CurrencyApi {

    @GET("latest")
    suspend fun getAllCurrency(
        @Query("api_key") apiKey: String,
    ): Call<List<CurrencyItemEntity>>
}