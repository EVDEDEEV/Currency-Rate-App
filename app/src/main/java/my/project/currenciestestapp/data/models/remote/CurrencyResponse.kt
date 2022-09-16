package my.project.currenciestestapp.data.models.remote


import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("base")
    val base: String?,
    @SerializedName("rates")
    val rates: Map<String, Double>?,
)