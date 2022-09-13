package my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites()
}