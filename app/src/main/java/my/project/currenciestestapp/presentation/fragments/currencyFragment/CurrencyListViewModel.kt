package my.project.currenciestestapp.presentation.fragments.currencyFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.project.currenciestestapp.data.api.RatesApi
import my.project.currenciestestapp.data.repository.RatesRepository
import my.project.currenciestestapp.presentation.models.CurrenciesUiModel
import my.project.currenciestestapp.presentation.models.RatesUiModel
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val ratesRepository: RatesRepository,
    private val api: RatesApi,
) : ViewModel() {

//    private val currencyStateFlow:  MutableStateFlow<ApiState> =
//        MutableStateFlow(ApiState.Empty)
//    val _currencyStateFlow: StateFlow<ApiState> = currencyStateFlow
//
//
//    fun getPost() = viewModelScope.launch {
//        currencyStateFlow.value = ApiState.Loading
//        currencyRepository.getPost()
//            .catch { e->
//                currencyStateFlow.value=ApiState.Failure(e)
//            }.collect { data->
//                currencyStateFlow.value=ApiState.Success(data)
//            }
//    }
//
//    }

    private val _currencies = MutableLiveData<List<RatesUiModel>>()
    val currencies: LiveData<List<RatesUiModel>> = _currencies

    private val _rates = MutableLiveData<List<CurrenciesUiModel>>()
    val rates: LiveData<List<CurrenciesUiModel>> = _rates

    //    fun getCurr() {
//        viewModelScope.launch(Dispatchers.IO) {
//            getCurrencies()
//        }
//    }
//
//    fun getCurr() = viewModelScope.launch {
//        currencyStateFlow.value = ApiState.Loading
//        currencyRepository.getCurrencies()
//    }
//
//}
    sealed class CurrencyEvent {
        class Success(val resultText: String) : CurrencyEvent()
        class Failure(val errorText: String) : CurrencyEvent()
        object Loading : CurrencyEvent()
        object Empty : CurrencyEvent()
    }



//    apikey: String, base: String
    fun getRates() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = ratesRepository.getRates()
            _currencies.postValue(result)
        }
    }

//    fun getCurrencies() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val res = currencyRepository.getCurrencies()
//            _rates.postValue(res)
//        }
//    }
}