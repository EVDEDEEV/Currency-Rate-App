package my.project.currenciestestapp.data.models.local

import androidx.room.Database
import androidx.room.RoomDatabase
import my.project.currenciestestapp.data.models.local.currenies.CurrencyDao
import my.project.currenciestestapp.data.models.local.currenies.CurrencyItemEntity


@Database(
    entities = [CurrencyItemEntity::class],
    version = 1)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

}