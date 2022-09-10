package my.project.currenciestestapp.data.models.local

import androidx.room.*

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyItems(currencyItems: List<CurrencyItemEntity>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertCurrencyItem(currencyItem: CurrencyItemEntity)
//
//    @Delete
//    suspend fun deleteCurrencyItem(currencyItem: CurrencyItemEntity)
//
//    @Query("DELETE FROM currency_items")
//    suspend fun deleteAllCurrencyItems()
//
//    @Query("SELECT * FROM currency_items")
//    fun getAllCurrencyItems(): List<CurrencyItemEntity>
}