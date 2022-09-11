package my.project.currenciestestapp.data.models.mainmodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_items")
data class MainEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val baseCurrency: String,
    val currencySymbol: String,
    val currencyAbbreviation: String,
    val currencyRate: Double,
)
