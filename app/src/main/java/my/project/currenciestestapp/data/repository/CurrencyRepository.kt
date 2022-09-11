package my.project.currenciestestapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import my.project.currenciestestapp.data.api.CurrenciesApi
import my.project.currenciestestapp.data.api.RatesApi
import my.project.currenciestestapp.data.models.remote.RatesResponse
import my.project.currenciestestapp.presentation.models.CurrenciesUiModel
import my.project.currenciestestapp.presentation.models.RatesUiModel
import retrofit2.Response
import java.util.concurrent.Flow
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
//    private val currencyDao: CurrencyDao,
    private val ratesApi: RatesApi,
    private val currenciesApi: CurrenciesApi,
) {

//
//        suspend fun getCurrencies() {
//        val dd = api.getCurrencies().body()
//        val ss = dd
//    }

    suspend fun getCurrencies(): List<CurrenciesUiModel> {
        val result = currenciesApi.getCurrencies().body()
        val currencies = result?.symbols?.entries?.mapIndexed { index, entry ->
            CurrenciesUiModel(
                id = index.inc(),
                symbols = entry.key,
                abbr = entry.value)
        }.orEmpty()
        return currencies
    }


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


    fun getPost(): kotlinx.coroutines.flow.Flow<Response<RatesResponse>> = flow {
        emit(ratesApi.getRates())
    }.flowOn(Dispatchers.IO)
}