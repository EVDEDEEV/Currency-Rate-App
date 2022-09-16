package my.project.currenciestestapp.presentation.fragments.favoritesFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity.FavoritesEntity
import my.project.currenciestestapp.data.repository.CurrencyListRepository
import my.project.currenciestestapp.data.repository.FavoriteListRepository
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val currencyRepository: CurrencyListRepository,
    private val favoritesRepository: FavoriteListRepository,
) : ViewModel() {

    val favorites: Flow<List<FavoritesEntity>> = favoritesRepository.getAllFavorites()

    fun addToFavorites(currencyName: String, rate: Double) {
        insertInFavoritesEntity(
            FavoritesEntity(
                favoritesCurrencyName = currencyName,
                favoriteRate = rate
            )
        )
    }

    private fun insertInFavoritesEntity(favoritesEntity: FavoritesEntity) = viewModelScope.launch {
        currencyRepository.insertItemInFavoritesList(favoritesEntity)
    }

    fun removeFromFavorites(favoriteName: String) = viewModelScope.launch {
        favoritesRepository.deleteFavoriteItem(favoriteName)
    }

    fun deleteAllFavoritesItems() = viewModelScope.launch {
        favoritesRepository.deleteAllFavorites()
    }
}