package my.project.currenciestestapp.data.models.roomDataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyDao
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyEntity
import my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity.FavoritesDao
import my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity.FavoritesEntity

@Database(entities = [CurrencyEntity::class, FavoritesEntity::class], version = AppDatabase.VERSION)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        const val VERSION = 2
        const val NAME = "app-db"
    }
    abstract fun getCurrencyDao(): CurrencyDao
    abstract fun getFavoritesDao(): FavoritesDao

}
