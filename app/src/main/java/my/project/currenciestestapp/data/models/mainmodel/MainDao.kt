package my.project.currenciestestapp.data.models.mainmodel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import my.project.currenciestestapp.data.models.local.currenies.CurrencyItemEntity

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyItems(mainItems: List<MainEntity>)


}