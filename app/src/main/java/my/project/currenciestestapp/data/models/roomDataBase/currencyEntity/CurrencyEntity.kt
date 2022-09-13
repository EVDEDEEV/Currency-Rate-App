package my.project.currenciestestapp.data.models.roomDataBase.currencyEntity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "currencyTable")
data class CurrencyEntity(
    @PrimaryKey
    @ColumnInfo(name = "currencyName")
    var currencyName: String,
    @ColumnInfo(name = "rate")
    var rate: Double,
)
