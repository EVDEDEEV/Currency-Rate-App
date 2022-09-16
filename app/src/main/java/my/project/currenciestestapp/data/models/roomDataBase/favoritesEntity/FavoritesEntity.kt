package my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_table")
data class FavoritesEntity(
    @PrimaryKey
    @ColumnInfo(name = "favorite_currency_name")
    var favoritesCurrencyName: String,
    @ColumnInfo(name = "favorite_rate")
    var favoriteRate: Double,
)