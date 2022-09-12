package my.project.currenciestestapp.data.repository

import my.project.currenciestestapp.data.api.CurrenciesApi
import my.project.currenciestestapp.data.api.RatesApi
import my.project.currenciestestapp.data.models.remote.Currency
import my.project.currenciestestapp.data.models.room.RateDao
import my.project.currenciestestapp.presentation.models.RatesUiModel
import javax.inject.Inject

class RatesRepository @Inject constructor(
    private val ratesDao: RateDao,
    private val ratesApi: RatesApi,
) {

//    // Remote-> Retrofit
//    suspend fun getCurrentExchangeRates() = ratesApi.getCurrentExchangeRates()
//    suspend fun getCurrencyTypes() = ratesApi.getCurrencyTypes()
//
//    // Local-> Room
//    suspend fun getSavedExchangeRates(): List<Currency> = rateDao.getAllList()
//    suspend fun updateAllExchangeRates(saveList: List<Currency>) = rateDao.updateAllRate(saveList)



//    suspend fun getRates(base: String): Resource<CurrencyResponse> {
//        return try {
//            val response = currenciesApi.getCurrencies(base)
//            val result = response.body()
//            if(response.isSuccessful && result != null) {
//                Resource.Success(result)
//            } else {
//                Resource.Error(response.message())
//            }
//        } catch(e: Exception) {
//            Resource.Error(e.message ?: "An error occurred")
//        }
//    }


//    suspend fun getCurrencies(): List<CurrenciesUiModel> {
//        val result = currenciesApi.getCurrencies().body()
//        val currencies = result?.symbols?.entries?.mapIndexed() { id, entry ->
//            CurrenciesUiModel(
//                id = id.inc(),
//                abbreviation = entry.key,
//                fullName = entry.value)
//        }.orEmpty()
//        return currencies
//    }
//    apikey: String, base: String
    suspend fun getRates(): List<RatesUiModel> {
        val result = ratesApi.getRates().body()
        val rates = result?.rates?.entries?.mapIndexed { index, entry ->
            RatesUiModel(
                id = index.inc(),
                currencyName = entry.key,
                rates = entry.value
            )
        }.orEmpty()
        return rates
    }


//    fun getPost(): kotlinx.coroutines.flow.Flow<Response<RatesResponse>> = flow {
//        emit(ratesApi.getRates())
//    }.flowOn(Dispatchers.IO)
}