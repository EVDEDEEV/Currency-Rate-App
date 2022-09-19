package my.project.currenciestestapp.data.models.remote

import com.google.gson.annotations.SerializedName


data class CurrencyNameResponse(
    @SerializedName("symbols")
    val symbols: Map<String, CurrencyName>?)

data class CurrencyName(
    @SerializedName("description")
    val description: String?,
)
