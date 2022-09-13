package my.project.currenciestestapp.data.repository

import my.project.currenciestestapp.data.api.RatesApi
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyEntity
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyDao
import javax.inject.Inject

class RatesRepository @Inject constructor(
    private val ratesDao: CurrencyDao,
    private val ratesApi: RatesApi,
) {

//    // Remote-> Retrofit
//    suspend fun getCurrentExchangeRates() = ratesApi.getCurrentExchangeRates()
//    suspend fun getCurrencyTypes() = ratesApi.getCurrencyTypes()
//
//    // Local-> Room
//    suspend fun getSavedExchangeRates(): List<Currency> = ratesDao.getAllList()
//    suspend fun updateAllExchangeRates(saveList: List<Currency>) = ratesDao.updateAllRate(saveList)

//    suspend fun getCurrencies(): List<CurrenciesUiModel> {
//        val result = currenciesApi.getCurrencies().body()
//        val currencies = result?.symbols?.entries?.mapIndexed() { id, entry ->
//            CurrenciesUiModel(
//                id = id.inc(),
//                abbreviation = entry.key,
//                fullName = entry.value)
//        }.orEmpty()
//        return currencies
//    }
//    apikey: String, base: String
//    suspend fun getRates(base: String): List<RatesUiModel> {
//        val result = ratesApi.getCurrency(base).body()
//        val base = result?.
//        val rates = result?.rates?.entries?.mapIndexed { index, entry ->
//                RatesUiModel(
//                    id = index.inc(),
//                    base = base
//                    currencyName = entry.key,
//                    rates = entry.value
//                )
//        }.orEmpty()
//        return rates
//    }

        suspend fun getRatesFromApi(base: String): List<CurrencyEntity> {
        val result = ratesApi.getCurrency(base).body()
        val currencyEntity = result?.rates?.entries?.map { entry ->
                CurrencyEntity(
                    currencyName = entry.key,
                    rate = entry.value
                )
        }.orEmpty()
        return currencyEntity
    }

    // Local-> Room
    suspend fun getSavedExchangeRates(): List<CurrencyEntity> = ratesDao.getAllList()
//    suspend fun updateAllExchangeRates(saveList: List<Currency>) = ratesDao.updateAllRate(saveList)

//    suspend fun getRatesFromAPI(base: String): List<RatesResponse> {
//        val ratesList: List<RatesResponse>
//        try {
//            val response = ratesApi.getCurrency(base).body()
//            if(response!=null){
//                ratesList = response.rates.entries.stream().flatMap {
//
//                }
//            }
//        } catch (exception: Exception) {
//            Log.i("MyTag", exception.message.toString())
//        }
//        return ratesList
//    }





//    suspend fun startMigration (base: String) {
//        val call = ratesApi?.getCurrency(base)
//        call.(object: Callback<ArrayList<CoffeeApiModel>> {
//            override fun onResponse(
//                call: Call<ArrayList<CoffeeApiModel>>,
//                response: Response<ArrayList<CoffeeApiModel>>
//            ) {
//
//
//                var loadCoffee: ArrayList<CoffeeApiModel>? = null
//
//                loadCoffee?.clear()
//
//                loadCoffee = (response.body() as ArrayList<CoffeeApiModel>?)!!
//
//
//
//                for (audit in loadCoffee) {
//
//                    audit.id?.let {
//                        CoffeeModel(
//                            it,
//                            audit.name.toString(),
//                            audit.image.toString(),
//                            audit.description.toString(),
//                            audit.price.toString()
//                        )
//                    }?.let {
//                        coffeeDataSource.insert(
//                            it
//                        )
//                    }
//                }
//                Toast.makeText(context, "ЗАГРУЗКА", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onFailure(call: Call<ArrayList<CoffeeApiModel>>, t: Throwable) {
//                Toast.makeText(context, "ОШИБКА! ВКЛЮЧИТЕ ИНТЕРНЕТ!", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
}