package my.project.currenciestestapp.data.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_items")
data class CurrencyItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val sourceCurrency: String = "",
    val currency: String = "",
    val rate: Double,
)