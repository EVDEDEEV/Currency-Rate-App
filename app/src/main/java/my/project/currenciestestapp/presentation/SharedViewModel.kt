package my.project.currenciestestapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import my.project.currenciestestapp.data.api.CurrencyApi
import my.project.currenciestestapp.data.repository.CurrencyRepositoryImpl
import my.project.currenciestestapp.presentation.models.RatesUiModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repo: CurrencyRepositoryImpl,
    private val api: CurrencyApi,
) : ViewModel() {

    private val currencies = MutableLiveData<List<RatesUiModel>>()

    init {
        getCurrencies()
    }


    fun getCurr() {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrencies()
        }
    }

    fun getCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.getCurrencies()
            currencies.postValue(result)
        }


    }


//    suspend fun getCurrencies(): List<RatesUiModel> {
//        return repo.getCurrencies()
//    }
}