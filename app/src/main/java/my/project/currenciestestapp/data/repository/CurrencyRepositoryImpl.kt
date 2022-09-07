package my.project.currenciestestapp.data.repository

import my.project.currenciestestapp.data.api.CurrencyApi
import my.project.currenciestestapp.data.models.local.CurrencyItemDao
import my.project.currenciestestapp.data.models.local.CurrencyItemEntity
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor (
    private val currencyItemDao: CurrencyItemDao,
    private val api: CurrencyApi,
) : CurrencyRepository {

    override suspend fun insertAllCurrencyItems(currencyItems: List<CurrencyItemEntity>) {
        currencyItemDao.insertCurrencyItems(currencyItems)
    }

    override suspend fun insertCurrencyItem(currencyItem: CurrencyItemEntity) {
        currencyItemDao.insertCurrencyItem(currencyItem)
    }

    override suspend fun deleteCurrencyItem(currencyItem: CurrencyItemEntity) {
        currencyItemDao.deleteCurrencyItem(currencyItem)
    }

    override suspend fun deleteAllCurrencyItems() {
        currencyItemDao.deleteAllCurrencyItems()
    }

    override suspend fun getAllCurrencyItems(): List<CurrencyItemEntity> {
        return currencyItemDao.getAllCurrencyItems()
    }

}