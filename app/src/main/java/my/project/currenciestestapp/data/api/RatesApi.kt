package my.project.currenciestestapp.data.api

import my.project.currenciestestapp.data.models.remote.RatesResponse
import my.project.currenciestestapp.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RatesApi {

    @GET("latest")
    suspend fun getRates(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("base") base: String = "USD",
    ): Response<RatesResponse>
}


