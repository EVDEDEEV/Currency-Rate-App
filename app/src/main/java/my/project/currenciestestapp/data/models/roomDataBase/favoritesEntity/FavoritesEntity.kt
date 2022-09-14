package my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoritesTable")
class FavoritesEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "favoriteCurrencyName")
    var favoritesCurrencyName: String,
    @ColumnInfo(name = "favoriteRate")
    var favoriteRate: Double,
)
