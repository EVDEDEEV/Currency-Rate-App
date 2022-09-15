package my.project.currenciestestapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.project.currenciestestapp.data.api.ApiKeyInterceptor
import my.project.currenciestestapp.data.api.CurrencyApi
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyDao
import my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity.FavoritesDao
import my.project.currenciestestapp.data.repository.CurrencyListRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "https://api.apilayer.com/exchangerates_data/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRatesApiInterface(retrofit: Retrofit): CurrencyApi {
        return retrofit.create(CurrencyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder().addInterceptor(ApiKeyInterceptor())
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideCurrencyRepository(currencyDao: CurrencyDao, favoritesDao: FavoritesDao, currencyApi: CurrencyApi) =
        CurrencyListRepository(currencyDao,currencyApi, favoritesDao)
}