package my.project.currenciestestapp.presentation.models

import my.project.currenciestestapp.data.models.local.currenies.CurrencyItemEntity

data class CurrencyUi(
    val id: Int?,
    val baseCurrency: String,
    val currencySymbol: String,
    val currencyAbbreviation: String,
    val currencyRate: Double,
)

//fun List<CurrencyItemEntity>.mapToUi(): List<CurrencyUi> {
//    return this.map {
//        CurrencyUi(
//            id = it.id,
//            currencyAbbreviation = it.abbreviation,
//            baseCurrency =
//        )
//    }
//}
