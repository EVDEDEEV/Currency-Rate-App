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
import my.project.currenciestestapp.data.models.remote.Rates
import my.project.currenciestestapp.data.repository.RatesRepository
import my.project.currenciestestapp.presentation.models.CurrenciesUiModel
import my.project.currenciestestapp.presentation.models.RatesUiModel
import my.project.currenciestestapp.utils.Resource
import javax.inject.Inject
import kotlin.math.round

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val repository: RatesRepository,
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

    private val _conversion = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    val conversion: StateFlow<CurrencyEvent> = _conversion


    sealed class CurrencyEvent {
        class Success(val resultText: String) : CurrencyEvent()
        class Failure(val errorText: String) : CurrencyEvent()
        object Loading : CurrencyEvent()
        object Empty : CurrencyEvent()
    }

//    fun convert(
//        amountStr: String,
//        fromCurrency: String,
//        toCurrency: String,
//    ) {
//        val fromAmount = amountStr.toFloatOrNull()
//        if (fromAmount == null) {
//            _conversion.value = CurrencyEvent.Failure("Not a valid amount")
//            return
//        }
//
//        viewModelScope.launch(Dispatchers.IO) {
//            _conversion.value = CurrencyEvent.Loading
//            when (val ratesResponse = repository.getRates(fromCurrency)) {
//                is Resource.Error -> _conversion.value =
//                    CurrencyEvent.Failure(ratesResponse.message!!)
//                is Resource.Success -> {
//                    val rates = ratesResponse.data!!.rates
//                    val rate = getRateForCurrency(toCurrency, rates)
//                    if (rate == null) {
//                        _conversion.value = CurrencyEvent.Failure("Unexpected error")
//                    } else {
//                        val convertedCurrency = round(fromAmount * rate * 100) / 100
//                        _conversion.value = CurrencyEvent.Success(
//                            "$fromAmount $fromCurrency = $convertedCurrency $toCurrency"
//                        )
//                    }
//                }
//            }
//        }
//    }


//рабочий
//    apikey: String, base: String
    fun getRates(base: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getRates(base)
            _currencies.postValue(result)
        }
    }


}