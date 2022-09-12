package my.project.currenciestestapp.data.models.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import my.project.currenciestestapp.data.models.remote.Currency


@Dao
interface RateDao {

    @Query("SELECT * from currencyTable")
    suspend fun getAllList(): List<Currency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAllRate(rateList: List<Currency>)
}