package my.project.currenciestestapp.presentation.fragments.currencyFragment

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import my.project.currenciestestapp.Constants.INTERNET_CONNECTION_ERROR
import my.project.currenciestestapp.CurrencyApplication
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyEntity
import my.project.currenciestestapp.data.repository.CurrencyListRepository
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val repository: CurrencyListRepository,
    private val application: CurrencyApplication,
) : ViewModel() {

    var connectionError = MutableLiveData<String>()

    var currencies: Flow<List<CurrencyEntity>> = repository.getSavedExchangeRates()
    private val sortedCurrencyListByNameASC: Flow<List<CurrencyEntity>> =
        repository.getSortFilterByNameASC()
    private val sortedCurrencyListByNameDESC: Flow<List<CurrencyEntity>> =
        repository.getSortFilterByNameDESC()
    private val sortedCurrencyListByRateASC: Flow<List<CurrencyEntity>> =
        repository.getSortFilterByRateASC()
    private val sortedCurrencyListByRateDESC: Flow<List<CurrencyEntity>> =
        repository.getSortFilterByRateDESC()

    fun getFilteredList(sortFilter: String?): Flow<List<CurrencyEntity>> {
        return when (sortFilter) {
            FilterArgs.FILTER_BY_NAME_ASC.sortFilter -> sortedCurrencyListByNameASC
            FilterArgs.FILTER_BY_NAME_DESC.sortFilter -> sortedCurrencyListByNameDESC
            FilterArgs.FILTER_BY_RATE_ASC.sortFilter -> sortedCurrencyListByRateASC
            FilterArgs.FILTER_BY_RATE_DESC.sortFilter -> sortedCurrencyListByRateDESC
            else -> currencies
        }
    }

    fun getRatesFromApi(base: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getRatesFromApi(base)
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                connectionError.postValue(INTERNET_CONNECTION_ERROR)
            }
        }
    }

    fun isHasInternetConnection(): Boolean {
        val connectivityManager = application.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activityNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activityNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }

        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }

        }
        return false
    }

    enum class FilterArgs(val sortFilter: String) {
        FILTER_BY_NAME_ASC("name_asc"),
        FILTER_BY_NAME_DESC("name_desc"),
        FILTER_BY_RATE_ASC("rate_asc"),
        FILTER_BY_RATE_DESC("rate_desc"),
    }
}