package my.project.currenciestestapp.presentation.fragments.currencyFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import my.project.currenciestestapp.data.api.RatesApi
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyEntity
import my.project.currenciestestapp.data.repository.RatesRepository
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val repository: RatesRepository,
    private val api: RatesApi,
) : ViewModel() {

    private val _currencies = MutableLiveData<List<CurrencyEntity>>()
    val currencies: LiveData<List<CurrencyEntity>> = _currencies

    private val _conversion = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    val conversion: StateFlow<CurrencyEvent> = _conversion


    sealed class CurrencyEvent {
        class Success(val resultText: String) : CurrencyEvent()
        class Failure(val errorText: String) : CurrencyEvent()
        object Loading : CurrencyEvent()
        object Empty : CurrencyEvent()
    }

    fun getRates(base: String) {
    viewModelScope.launch(Dispatchers.IO) {
        val result = repository.getRatesFromApi(base)
        _currencies.postValue(result)
        }
    }

    fun getRatesFromDb(base: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getRates(base)
            repository.getSavedExchangeRates()
        }
    }
}