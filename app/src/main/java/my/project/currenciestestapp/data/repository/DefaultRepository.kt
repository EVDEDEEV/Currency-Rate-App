package my.project.currenciestestapp.data.repository

import kotlinx.coroutines.flow.Flow
import my.project.currenciestestapp.data.api.CurrencyApi
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyDao
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyEntity
import my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity.FavoritesDao
import my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity.FavoritesEntity
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val ratesDao: CurrencyDao,
    private val currencyApi: CurrencyApi,
    private val favoritesDao: FavoritesDao,
) {

    suspend fun getRatesFromApi(base: String): List<CurrencyEntity> {
        val result = currencyApi.getCurrency(base).body()
        val currencyEntity = result?.rates?.entries?.map { entry ->
            CurrencyEntity(
                currencyName = entry.key,
                rate = entry.value
            )
        }.orEmpty()
        return currencyEntity
    }

    suspend fun insert(favoritesEntity: FavoritesEntity) {
        favoritesDao.addToFavorites(favoritesEntity)
    }

    fun getAllFavorites(): Flow<List<FavoritesEntity>> {
        return favoritesDao.getAllFavorites()
    }

    // Local-> Room
    suspend fun getSavedExchangeRates(): List<CurrencyEntity> = ratesDao.getAllCurrency()
//    suspend fun updateAllExchangeRates(saveList: List<Currency>) = ratesDao.updateAllRate(saveList)


}