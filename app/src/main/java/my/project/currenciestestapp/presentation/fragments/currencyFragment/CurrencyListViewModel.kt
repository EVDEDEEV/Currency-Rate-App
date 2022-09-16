package my.project.currenciestestapp.presentation.fragments.currencyFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyEntity
import my.project.currenciestestapp.data.repository.CurrencyListRepository
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val repository: CurrencyListRepository,
) : ViewModel() {

    val currencies: Flow<List<CurrencyEntity>> = repository.getSavedExchangeRates()

    private val sortedCurrencyListByNameASC: Flow<List<CurrencyEntity>> = repository.getSortFilterByNameASC()
    private val sortedCurrencyListByNameDESC: Flow<List<CurrencyEntity>> = repository.getSortFilterByNameDESC()
    private val sortedCurrencyListByRateASC: Flow<List<CurrencyEntity>> = repository.getSortFilterByRateASC()
    private val sortedCurrencyListByRateDESC: Flow<List<CurrencyEntity>> = repository.getSortFilterByRateDESC()

    fun getFilteredList(sortFilter: String?):Flow<List<CurrencyEntity>> {
        return when(sortFilter) {
            "name_asc" -> sortedCurrencyListByNameASC
            "name_desc" -> sortedCurrencyListByNameDESC
            "rate_asc" -> sortedCurrencyListByRateASC
            "rate_desc" -> sortedCurrencyListByRateDESC
            else -> currencies
        }
    }

    fun getRatesFromApi(base: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRatesFromApi(base)
        }
    }
}