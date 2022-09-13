package my.project.currenciestestapp.data.models.roomDataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyDao
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyEntity

@Database(entities = [CurrencyEntity::class], version = AppDatabase.VERSION)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        const val VERSION = 1
        const val NAME = "app-db"
    }
    abstract fun getRateDao(): CurrencyDao
}
