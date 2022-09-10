package my.project.currenciestestapp.data.repository

import kotlinx.coroutines.flow.asFlow
import my.project.currenciestestapp.data.api.CurrencyApi
import my.project.currenciestestapp.presentation.models.RatesUiModel
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
//    private val currencyDao: CurrencyDao,
    private val api: CurrencyApi,
) {

//
//        suspend fun getCurrencies() {
//        val dd = api.getCurrencies().body()
//        val ss = dd
//    }

    suspend fun getCurrencies(): List<RatesUiModel> {
        val result = api.getCurrencies().body()
        val currencies = result?.rates?.entries?.mapIndexed { index, entry ->
            RatesUiModel(
                id = index.inc(),
                currencyName = entry.key,
                rates = entry.value
            )
        }.orEmpty()
        return currencies
    }
}