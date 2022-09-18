package my.project.currenciestestapp.data.models.remote


import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("base")
    val baseCurrency: String?,
    @SerializedName("rates")
    val currencyRates: Map<String, Double>?,
)



