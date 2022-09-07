package my.project.currenciestestapp.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import my.project.currenciestestapp.data.models.local.CurrencyItemEntity
import my.project.currenciestestapp.data.repository.CurrencyRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repo: CurrencyRepositoryImpl
) : ViewModel() {

    suspend fun getList(): List<CurrencyItemEntity> {
        return repo.getAllCurrencyItems()
    }
}