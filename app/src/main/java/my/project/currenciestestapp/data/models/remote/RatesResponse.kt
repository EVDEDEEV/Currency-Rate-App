package my.project.currenciestestapp.data.models.remote


import com.google.gson.annotations.SerializedName
import my.project.currenciestestapp.data.api.CurrencyApi
import my.project.currenciestestapp.presentation.models.RatesUiModel

data class RatesResponse(
    @SerializedName("base")
    val base: String?,
    @SerializedName("rates")
    val rates: Map<String, Double>,
)


//fun mapToIU() {
//
//}

//fun RatesResponse.mapToUi(): List<RatesUiModel> = this?.base?.map { rates ->
//    RatesUiModel(
//        base = base.orEmpty(),
//        rates = rates.
//    )
//
//}

//)








//}











