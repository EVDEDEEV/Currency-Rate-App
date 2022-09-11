package my.project.currenciestestapp.data.models.local.rates

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rates_items")
data class RatesItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val base: String,
    val rate: Double
)
