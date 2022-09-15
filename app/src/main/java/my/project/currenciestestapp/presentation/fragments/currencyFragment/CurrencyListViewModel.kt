package my.project.currenciestestapp.presentation.fragments.currencyFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyEntity
import my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity.FavoritesEntity
import my.project.currenciestestapp.data.repository.DefaultRepository
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val repository: DefaultRepository,
) : ViewModel() {

    val currencies: Flow<List<CurrencyEntity>> = repository.getSavedExchangeRates()

    fun getRatesFromApi(base: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRatesFromApi(base)
        }
    }

//    fun getRatesFromDb() {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getSavedExchangeRates()
//        }
//    }
}