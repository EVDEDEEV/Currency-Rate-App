package my.project.currenciestestapp.data.models.local

import androidx.room.Database
import androidx.room.RoomDatabase
import my.project.currenciestestapp.data.models.local.rates.RatesDao
import my.project.currenciestestapp.data.models.local.rates.RatesItem


@Database(
    entities = [RatesItem::class],
    version = 1)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun ratesDao(): RatesDao

}