package my.project.currenciestestapp.data.models.roomDataBase.currencyEntity

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currency_table")
    fun getAllCurrency(): Flow<List<CurrencyEntity>>

    @Query("SELECT * FROM currency_table ORDER BY currency_name ASC")
     fun getSortedAscendingCurrencyListByName(): Flow<List<CurrencyEntity>>

    @Query("SELECT * FROM currency_table ORDER BY currency_name DESC")
     fun getSortedDescendingCurrencyListByName(): Flow<List<CurrencyEntity>>

    @Query("SELECT * FROM currency_table ORDER BY rate ASC")
     fun getSortedAscendingCurrencyListByRate(): Flow<List<CurrencyEntity>>

    @Query("SELECT * FROM currency_table ORDER BY rate DESC")
     fun getSortedDescendingCurrencyListByRate(): Flow<List<CurrencyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencies: List<CurrencyEntity?>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAllRate(rateList: List<CurrencyEntity>)

}