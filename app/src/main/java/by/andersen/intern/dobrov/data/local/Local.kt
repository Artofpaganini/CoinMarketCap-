package by.andersen.intern.dobrov.data.local

import by.andersen.intern.dobrov.domain.model.pricemodel.CryptoData
import io.reactivex.Completable
import io.reactivex.Observable

interface Local {

    val allCryptoDataDB: Observable<List<CryptoData>>

    fun insertCryptoDataDB(cryptoData: CryptoData): Completable

    fun deleteAllCryptoDataFromDB(): Completable
}