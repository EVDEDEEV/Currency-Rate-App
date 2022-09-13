package my.project.currenciestestapp.data.models.roomDataBase.currencyEntity

import androidx.room.*


@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currencyTable")
    suspend fun getAllList(): List<CurrencyEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAllRate(rateList: List<CurrencyEntity>)

}