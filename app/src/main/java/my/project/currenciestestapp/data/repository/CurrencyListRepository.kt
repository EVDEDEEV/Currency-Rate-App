package my.project.currenciestestapp.data.repository

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

    suspend fun getRatesFromApi(base: String) {
        val result = currencyApi.getCurrency(base).body()
        val currencyEntity = result?.rates?.entries?.map { entry ->
            CurrencyEntity(
                currencyName = entry.key,
                rate = entry.value
            )
        }.orEmpty()
        currencyDao.insertAll(currencyEntity)
    }

    suspend fun insertItemInFavoritesList(favoritesEntity: FavoritesEntity) {
        favoritesDao.addToFavorites(favoritesEntity)
    }

    fun getSavedExchangeRates(): Flow<List<CurrencyEntity>> =
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
