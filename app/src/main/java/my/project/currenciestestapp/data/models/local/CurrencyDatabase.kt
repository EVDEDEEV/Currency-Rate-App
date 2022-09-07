package my.project.currenciestestapp.data.models.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [CurrencyItemEntity::class],
    version = 1)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyItemDao

}