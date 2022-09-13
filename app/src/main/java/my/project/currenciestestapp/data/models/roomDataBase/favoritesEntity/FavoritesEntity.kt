package my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoritesTable")
data class FavoritesEntity(
    @PrimaryKey
    @ColumnInfo(name = "currencyName")
    var currencyName: String,
    @ColumnInfo(name = "rate")
    var rate: Double,
)
