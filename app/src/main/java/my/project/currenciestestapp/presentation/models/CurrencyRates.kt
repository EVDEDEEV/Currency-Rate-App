package my.project.currenciestestapp.presentation.models

import com.google.gson.annotations.SerializedName

data class CurrencyRates(
    var usd: HashMap<String, Double> = hashMapOf()
)
