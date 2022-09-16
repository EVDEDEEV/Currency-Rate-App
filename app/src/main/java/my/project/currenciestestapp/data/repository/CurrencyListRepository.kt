package my.project.currenciestestapp.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import kotlinx.coroutines.flow.Flow
import my.project.currenciestestapp.CurrencyApplication
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
    private val application: CurrencyApplication
) {

    fun isOnline(): Boolean {
        val connectivityManager = application.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activityNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activityNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }

        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }

        }
        return false
    }

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
