package my.project.currenciestestapp.data.api

import my.project.currenciestestapp.data.models.remote.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface CurrencyApi {

    @GET("latest")
    suspend fun getCurrency(
        @Query("base") base: String,
    ): Response<CurrencyResponse>
}


