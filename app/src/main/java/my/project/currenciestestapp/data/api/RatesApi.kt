package my.project.currenciestestapp.data.api

import android.provider.SyncStateContract
import my.project.currenciestestapp.data.models.remote.RatesResponse
import my.project.currenciestestapp.utils.Constants
import my.project.currenciestestapp.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RatesApi {

    @GET("latest")
    suspend fun getCurrency(
//        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("base") base: String,
    ): Response<RatesResponse>
}


