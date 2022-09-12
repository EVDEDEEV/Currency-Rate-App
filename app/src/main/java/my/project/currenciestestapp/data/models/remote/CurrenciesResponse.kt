package my.project.currenciestestapp.data.models.remote

import com.google.gson.annotations.SerializedName

data class CurrenciesResponse(
    @SerializedName("symbols")
    var symbols: Map<String, String>,
    )
