package my.project.currenciestestapp.data.models.local.currenies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import my.project.currenciestestapp.presentation.models.CurrenciesUiModel

//@Entity(tableName = "currency_items")
//data class CurrencyItemEntity(
//    @PrimaryKey(autoGenerate = true)
//    val id: Int? = null,
//    @ColumnInfo(name = )
//    val sourceCurrency: String = "",
//    val currency: String = "",
//    val rates: Double,

@Entity(tableName = "currency_items")
data class CurrencyItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "symb")
    var symbol: String,
    @ColumnInfo(name = "SYMBOL")
    var abbreviation: String,
    @ColumnInfo(name = "SUCCESS")
    var success: Boolean,
)
