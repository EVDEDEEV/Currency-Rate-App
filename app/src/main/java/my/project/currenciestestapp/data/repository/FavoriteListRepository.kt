package my.project.currenciestestapp.data.repository

import kotlinx.coroutines.flow.Flow
import my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity.FavoritesDao
import my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity.FavoritesEntity
import javax.inject.Inject

class FavoriteListRepository @Inject constructor(
    private val favoritesDao: FavoritesDao) {

    fun getAllFavorites(): Flow<List<FavoritesEntity>> {
        return favoritesDao.getAllFavorites()
    }

    suspend fun deleteFavoriteItem(itemName: String) {
        favoritesDao.deleteFavoriteByName(itemName)
    }

    suspend fun deleteAllFavorites() {
        favoritesDao.deleteAllItemsFromFavorites()
    }
}