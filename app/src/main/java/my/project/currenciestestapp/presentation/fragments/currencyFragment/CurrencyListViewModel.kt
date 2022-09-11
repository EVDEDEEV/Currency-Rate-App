package my.project.currenciestestapp.presentation.fragments.currencyFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import my.project.currenciestestapp.data.api.RatesApi
import my.project.currenciestestapp.data.repository.CurrencyRepository
import my.project.currenciestestapp.utils.ApiState
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val api: RatesApi,
) : ViewModel() {

    private val currencyStateFlow:  MutableStateFlow<ApiState> =
        MutableStateFlow(ApiState.Empty)
    val _currencyStateFlow: StateFlow<ApiState> = currencyStateFlow


    fun getPost() = viewModelScope.launch {
        currencyStateFlow.value = ApiState.Loading
        currencyRepository.getPost()
            .catch { e->
                currencyStateFlow.value=ApiState.Failure(e)
            }.collect { data->
                currencyStateFlow.value=ApiState.Success(data)
            }
    }

    }

//    val currencies = MutableStateFlow(List(RatesUiModel(0, "USD", 0.0)))

    //        val currencies = MutableLiveData<List<RatesUiModel>>()
//    private val _currencies = MutableLiveData<List<RatesUiModel>>()
//    val currencies: LiveData<List<RatesUiModel>> = _currencies
//
//    private val _rates = MutableLiveData<List<CurrenciesUiModel>>()
//    val rates: LiveData<List<CurrenciesUiModel>> = _rates

//    fun getCurr() {
//        viewModelScope.launch(Dispatchers.IO) {
//            getCurrencies()
//        }
//    }

//    fun getCurr() = viewModelScope.launch {
//        currencyStateFlow.value = ApiState.Loading
//        currencyRepository.getCurrencies()
//    }

//}

//    fun getRates() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val result = currencyRepository.getRates()
//            _currencies.postValue(result)
//        }
//    }
//
//    fun getCurrencies() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val res = currencyRepository.getCurrencies()
//            _rates.postValue(res)
//        }
//    }
//}