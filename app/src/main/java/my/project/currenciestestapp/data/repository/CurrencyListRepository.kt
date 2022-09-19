package my.project.currenciestestapp.data.repository

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
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

    suspend fun getRatesFromApi(baseCurrency: String, amount: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = async {
                currencyApi.getCurrencyRates(baseCurrency, amount).body()
            }
            val result2 = async {
                currencyApi.getCurrencies().body()
            }
            val currencyEntity = result.await()?.currencyRates?.entries?.map { it ->
                result2.await()?.symbols?.getValue(it.key)?.description?.let { it1 ->
                    CurrencyEntity(
                        currencyName = it.key,
                        rate = it.value,
                        description = it1)
                }
            }
            if (currencyEntity != null) {
                currencyDao.insertAll(currencyEntity)
            }
        }
    }




//suspend fun adr(baseCurrency: String, amount: String) = CoroutineScope(Dispatchers.IO).launch {
//    val result = currencyApi.getCurrencyRates(baseCurrency, amount).body()
//    val result2 = currencyApi.getCurrencies().body()
//
//}

//    val currencyEntity = result?.currencyRates?.entries?.map { entry ->
//        CurrencyEntity(
//            currencyName = entry.key,
//            rate = entry.value,
//        )
//    }.orEmpty()
//    currencyDao.insertAll(currencyEntity)
//}


//    suspend fun getRatesFromApi(base: String) {
//        val result = currencyApi.getCurrencyRates(base).body()
//        val res2 = currencyApi.getCurrencies().body()
//        val currencyEntity = result?.currencyRates?.entries?.map { entry ->
//            res2?.symbols?.forEach { ent2->
//                ent2.value
//            }?.let {
//                CurrencyEntity(
//                    currencyName = entry.key,
//                    rate = entry.value,
//                    description = it
//                )
//            }
//        }.orEmpty()
//        currencyDao.insertAll(currencyEntity)
//    }

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