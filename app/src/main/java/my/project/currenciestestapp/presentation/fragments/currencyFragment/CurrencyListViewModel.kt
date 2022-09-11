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
import my.project.currenciestestapp.data.api.CurrencyApi
import my.project.currenciestestapp.data.repository.CurrencyRepository
import my.project.currenciestestapp.presentation.models.RatesUiModel
import my.project.currenciestestapp.utils.ApiState
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val api: CurrencyApi,
) : ViewModel() {

//    private val currencyStateFlow: MutableStateFlow<ApiState> =
//        MutableStateFlow(ApiState.Empty)
//    val _currencyStateFlow: StateFlow<ApiState> = currencyStateFlow

//    val currencies = MutableStateFlow(List(RatesUiModel(0, "USD", 0.0)))

    //        val currencies = MutableLiveData<List<RatesUiModel>>()
    private val _currencies = MutableLiveData<List<RatesUiModel>>()
    val currencies: LiveData<List<RatesUiModel>> = _currencies

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

    fun getCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = currencyRepository.getCurrencies()
            _currencies.postValue(result)
        }
    }
}