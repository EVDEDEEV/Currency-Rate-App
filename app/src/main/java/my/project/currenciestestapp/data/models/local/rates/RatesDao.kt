package my.project.currenciestestapp.data.models.local.rates

import androidx.room.*


@Dao
interface RatesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyItems(exchangeItems: List<RatesItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExchangeItem(exchangeItem: RatesItem)

    @Delete
    suspend fun deleteExchangeItem(exchangeItem: RatesItem)

    @Query("DELETE FROM rates_items")
    suspend fun deleteAllCurrencyItems()

    @Query("DELETE FROM rates_items WHERE base = :selectedBaseCurrency")
    suspend fun deleteAllCurrencyItems(selectedBaseCurrency: String)

    @Query("SELECT * FROM rates_items")
    fun getAllExchangeItems(): List<RatesItem>

    @Query("SELECT * FROM rates_items WHERE base = :selectedBaseCurrency")
    fun getAllExchangeItems(selectedBaseCurrency: String): List<RatesItem>
}