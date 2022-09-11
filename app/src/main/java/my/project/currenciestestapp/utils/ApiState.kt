package my.project.currenciestestapp.utils

import my.project.currenciestestapp.presentation.models.RatesUiModel

sealed class ApiState{
    object Loading : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    class Success(val data:List<RatesUiModel>) : ApiState()
    object Empty : ApiState()
}
