package by.andersen.intern.dobrov.data.remote

import by.andersen.intern.dobrov.domain.model.imagemodel.ResponseCryptoImage
import by.andersen.intern.dobrov.domain.model.pricemodel.CryptoData
import by.andersen.intern.dobrov.domain.model.pricemodel.ResponseCryptoPrice
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class RemoteDataImpl @Inject constructor(private val apiService: ApiService) : Remote {

    private val API_KEY = "7b5e2c28-2b19-4055-8dc0-d58108f09948"

    override fun requestDataCurrency(): Observable<List<CryptoData>> {
        return apiService.getCryptoCurrencyList(API_KEY)
                .map { obj: ResponseCryptoPrice -> obj.data }
                .toObservable()
                .flatMap { cryptoData: List<CryptoData> ->
                    val list: MutableList<Int> = ArrayList()
                    cryptoData.map { list.add(it.id) }
                    requestCurrenciesImages(converterListIdToStringId(list), cryptoData)
                }
                .subscribeOn(Schedulers.io())
    }

    private fun requestCurrenciesImages(id: String, cryptoData: List<CryptoData>): Observable<List<CryptoData>> {
        return apiService.getCryptoCurrencyImages(id, API_KEY)
                .toObservable()
                .map { responseCryptoImage: ResponseCryptoImage ->
                    val hashMap = responseCryptoImage.data
                    cryptoData.map { it.logo = hashMap[it.id.toString()]!!.logo }
                    cryptoData
                }
    }

    private fun converterListIdToStringId(list: List<Int>): String = list.joinToString(separator = ",")

}