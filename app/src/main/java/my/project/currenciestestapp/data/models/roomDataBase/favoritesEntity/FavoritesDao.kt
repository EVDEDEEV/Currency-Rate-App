package my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(FavoritesEntity: FavoritesEntity)

    @Query("SELECT * FROM favorites_table")
    fun getAllFavorites(): Flow<List<FavoritesEntity>>

    @Query("DELETE FROM favorites_table WHERE favorite_currency_name = :favoriteCurrencyName")
    suspend fun deleteFavoriteByName(favoriteCurrencyName: String)

    @Query("DELETE FROM favorites_table")
    suspend fun deleteAllItemsFromFavorites()
}