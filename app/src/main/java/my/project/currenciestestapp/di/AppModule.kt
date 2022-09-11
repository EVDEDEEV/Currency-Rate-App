package my.project.currenciestestapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import my.project.currenciestestapp.data.api.CurrenciesApi
import my.project.currenciestestapp.data.api.RatesApi
import my.project.currenciestestapp.data.models.local.CurrencyDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.apilayer.com/exchangerates_data/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRatesApiInterface(retrofit: Retrofit): RatesApi {
        return retrofit.create(RatesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCurrenciesApiInterface(retrofit: Retrofit): CurrenciesApi {
        return retrofit.create(CurrenciesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

//    @Provides
//    @Singleton
//    fun provideRepository(currencyRepo: CurrencyRepositoryImpl) = CurrencyRepositoryImpl(
//        provideApiInterface(provideRetrofit()))


//    @Singleton
//    @Provides
//    fun provideCurrencyRepository(ratesDao: CurrencyDao, currencyApi: CurrencyApi) =
//        CurrencyRepositoryImpl(ratesDao, currencyApi) as CurrencyRepository

    @Singleton
    @Provides
    fun provideCurrencyConverterDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context, CurrencyDatabase::class.java,
        "currency_converter_db"
    ).build()


    @Singleton
    @Provides
    fun provideCurrencyDao(
        currencyDatabase: CurrencyDatabase,
    ) = currencyDatabase.ratesDao()
}