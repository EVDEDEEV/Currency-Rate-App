package my.project.currenciestestapp.data.models.room

import androidx.room.Database
import androidx.room.RoomDatabase
import my.project.currenciestestapp.data.models.remote.Currency

@Database(entities = [Currency::class], version = AppDatabase.VERSION)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        const val VERSION = 1
        const val NAME = "app-db"
    }
    abstract fun getRateDao(): RateDao
}
