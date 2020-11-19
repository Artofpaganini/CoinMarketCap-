package by.andersen.intern.dobrov.data.remote

import by.andersen.intern.dobrov.domain.model.pricemodel.CryptoData
import io.reactivex.Observable

interface Remote {

    fun requestDataCurrency(): Observable<List<CryptoData>>

}