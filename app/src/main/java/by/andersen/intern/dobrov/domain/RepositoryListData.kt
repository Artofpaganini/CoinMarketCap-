package by.andersen.intern.dobrov.domain

import by.andersen.intern.dobrov.domain.model.pricemodel.CryptoData
import io.reactivex.Completable
import io.reactivex.Observable

interface RepositoryListData {

    fun loadCryptoList(): Observable<List<CryptoData>>
    fun loadCryptoListToDB(cryptoData: CryptoData): Completable
}