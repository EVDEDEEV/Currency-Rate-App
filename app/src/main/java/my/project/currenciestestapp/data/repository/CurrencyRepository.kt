package my.project.currenciestestapp.data.repository

import my.project.currenciestestapp.data.api.CurrencyApi
import my.project.currenciestestapp.data.models.local.CurrencyItemDao
import my.project.currenciestestapp.data.models.local.CurrencyItemEntity

interface CurrencyRepository {

    suspend fun insertAllCurrencyItems(currencyItems: List<CurrencyItemEntity>)

    suspend fun insertCurrencyItem(currencyItem: CurrencyItemEntity)

    suspend fun deleteCurrencyItem(currencyItem: CurrencyItemEntity)

    suspend fun deleteAllCurrencyItems()

    suspend fun getAllCurrencyItems(): List<CurrencyItemEntity>

//    suspend fun getCurrencies(): Resource<CurrencyItemEntity>
}