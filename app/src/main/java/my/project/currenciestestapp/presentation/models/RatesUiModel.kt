package my.project.currenciestestapp.presentation.models

import com.google.gson.annotations.SerializedName
import my.project.currenciestestapp.data.models.local.CurrencyItemEntity

data class RatesUiModel(
    val id: Int,
    val currencyName: String,
    val rates: Double
)
//data class RatesUiModel(
//    @SerializedName("success")
//    val success: Boolean,
//    @SerializedName("rates")
//    val rates: Map<String, Double>
//)

//    fun List<RatesUiModel>.mapToEntity(): List<CurrencyItemEntity> {
//        return this.map {
//            CurrencyItemEntity(
//                id = it.id
//            )
//        }
//
//
//}

