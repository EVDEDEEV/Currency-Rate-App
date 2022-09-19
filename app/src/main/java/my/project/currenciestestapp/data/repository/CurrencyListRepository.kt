package my.project.currenciestestapp.data.repository

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import my.project.currenciestestapp.data.api.CurrencyApi
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyDao
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyEntity
import my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity.FavoritesDao
import my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity.FavoritesEntity
import javax.inject.Inject

class CurrencyListRepository @Inject constructor(
    private val currencyDao: CurrencyDao,
    private val currencyApi: CurrencyApi,
    private val favoritesDao: FavoritesDao,
) {

    suspend fun getResponseFromApis(baseCurrency: String, amount: String) {

        CoroutineScope(Dispatchers.IO).launch {
            val resultForRates = currencyApi.getCurrencyRates(baseCurrency, amount)
            val ratesResultBody = async {
                resultForRates.body()
            }

            val resultForCurrencyName = currencyApi.getCurrencies()
            val nameResultBody = async {
                resultForCurrencyName.body()
            }
            if (resultForRates.isSuccessful && resultForCurrencyName.isSuccessful) {
                val currencyEntity =
                    ratesResultBody.await()?.currencyRates?.entries?.map { it ->
                        nameResultBody.await()?.symbols?.getValue(it.key)?.description?.let { it1 ->
                            CurrencyEntity(
                                currencyName = it.key,
                                rate = it.value,
                                description = it1
                            )
                        }

                    }
                if (currencyEntity != null) {
                    currencyDao.insertAll(currencyEntity)
                }
            }
        }
    }

    suspend fun insertItemInFavoritesList(favoritesEntity: FavoritesEntity) {
        favoritesDao.addToFavorites(favoritesEntity)
    }

    fun getSavedCurrencyRates(): Flow<List<CurrencyEntity>> =
        currencyDao.getAllCurrency()

    fun getSortFilterByNameASC(): Flow<List<CurrencyEntity>> =
        currencyDao.getSortedAscendingCurrencyListByName()

    fun getSortFilterByNameDESC(): Flow<List<CurrencyEntity>> =
        currencyDao.getSortedDescendingCurrencyListByName()

    fun getSortFilterByRateASC(): Flow<List<CurrencyEntity>> =
        currencyDao.getSortedAscendingCurrencyListByRate()

    fun getSortFilterByRateDESC(): Flow<List<CurrencyEntity>> =
        currencyDao.getSortedDescendingCurrencyListByRate()
}

