package my.project.currenciestestapp.presentation.fragments.favoritesFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity.FavoritesEntity
import my.project.currenciestestapp.data.repository.RatesRepository
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: RatesRepository,
) : ViewModel() {

    private val _favorites = MutableLiveData<List<FavoritesEntity>>()
    val favorites: LiveData<List<FavoritesEntity>> = _favorites

    fun addToFavor(id: Int, currencyName: String, rate: Double) {
        insertInFavoritesEntity(
            FavoritesEntity(
                id = id,
                favoritesCurrencyName = currencyName,
                favoriteRate = rate
            )
        )
    }

    private fun insertInFavoritesEntity(favoritesEntity: FavoritesEntity) = viewModelScope.launch {
        repository.insert(favoritesEntity)
    }

//    val loadFavoritesEntity = repository.getAllFavorites()
//    val ss = loadFavoritesEntity

    fun getFavoritesFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val favorites = repository.getAllFavorites()
            _favorites.postValue(favorites)
        }
    }


}