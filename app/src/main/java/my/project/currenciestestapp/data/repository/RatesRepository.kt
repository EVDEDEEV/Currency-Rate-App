package my.project.currenciestestapp.data.repository

import androidx.lifecycle.LiveData
import my.project.currenciestestapp.data.api.RatesApi
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyDao
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyEntity
import my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity.FavoritesDao
import my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity.FavoritesEntity
import javax.inject.Inject

class RatesRepository @Inject constructor(
    private val ratesDao: CurrencyDao,
    private val ratesApi: RatesApi,
    private val favoritesDao: FavoritesDao,
) {

    suspend fun getRatesFromApi(base: String): List<CurrencyEntity> {
        val result = ratesApi.getCurrency(base).body()
        val currencyEntity = result?.rates?.entries?.map { entry ->
            CurrencyEntity(
                currencyName = entry.key,
                rate = entry.value
            )
        }.orEmpty()
        return currencyEntity
    }

//    suspend fun addToFav(currency: CurrencyEntity) {
//        favoritesDao.addToFavorites(currency)
//    }

    fun insert(favoritesEntity: FavoritesEntity): FavoritesEntity {
        return favoritesEntity
    }

     fun getAllFavorites(): LiveData<List<FavoritesEntity>> = favoritesDao.getAllFavorites()

    // Local-> Room
    suspend fun getSavedExchangeRates(): List<CurrencyEntity> = ratesDao.getAllCurrency()
//    suspend fun updateAllExchangeRates(saveList: List<Currency>) = ratesDao.updateAllRate(saveList)


}