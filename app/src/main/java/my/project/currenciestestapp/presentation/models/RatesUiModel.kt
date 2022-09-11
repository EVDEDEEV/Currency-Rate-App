package my.project.currenciestestapp.presentation.models

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

