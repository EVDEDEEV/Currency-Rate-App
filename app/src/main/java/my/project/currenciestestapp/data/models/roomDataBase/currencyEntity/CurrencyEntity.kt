package my.project.currenciestestapp.data.models.roomDataBase.currencyEntity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_table")
data class CurrencyEntity(
    @PrimaryKey
    @ColumnInfo(name = "currency_name")
    var currencyName: String,
    @ColumnInfo(name = "rate")
    var rate: Double,
)
