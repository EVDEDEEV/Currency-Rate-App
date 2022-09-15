package my.project.currenciestestapp.presentation.fragments.favoritesFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity.FavoritesEntity
import my.project.currenciestestapp.data.repository.DefaultRepository
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: DefaultRepository,
) : ViewModel() {

    val favorites: Flow<List<FavoritesEntity>> = repository.getAllFavorites()

    fun addToFavor(currencyName: String, rate: Double) {
        insertInFavoritesEntity(
            FavoritesEntity(
                favoritesCurrencyName = currencyName,
                favoriteRate = rate
            )
        )
    }

    private fun insertInFavoritesEntity(favoritesEntity: FavoritesEntity) = viewModelScope.launch {
        repository.insert(favoritesEntity)
    }


    fun removeFromFavorites(favoriteName: String) = viewModelScope.launch {
        repository.deleteFavoriteItem(favoriteName)
    }

    fun deleteAllFavoritesItems() = viewModelScope.launch {
        repository.deleteAllFavorites()
    }
}