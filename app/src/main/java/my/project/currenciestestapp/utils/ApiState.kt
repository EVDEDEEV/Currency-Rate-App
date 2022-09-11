package my.project.currenciestestapp.utils

import my.project.currenciestestapp.data.models.remote.RatesResponse
import my.project.currenciestestapp.presentation.models.RatesUiModel
import retrofit2.Response

sealed class ApiState{
    object Loading : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    class Success(val data: Response<RatesResponse>) : ApiState()
    object Empty : ApiState()
}
