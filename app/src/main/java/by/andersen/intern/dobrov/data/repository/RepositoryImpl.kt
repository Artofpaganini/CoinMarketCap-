package by.andersen.intern.dobrov.data.repository

import by.andersen.intern.dobrov.data.local.Local
import by.andersen.intern.dobrov.data.remote.Remote
import by.andersen.intern.dobrov.domain.RepositoryFavoriteData
import by.andersen.intern.dobrov.domain.RepositoryListData
import by.andersen.intern.dobrov.domain.model.pricemodel.CryptoData
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remote: Remote, private val local: Local) : RepositoryListData, RepositoryFavoriteData {

    override fun loadCryptoList(): Observable<List<CryptoData>> {
        return remote.requestDataCurrency()
                .flatMap { item: List<CryptoData> -> Observable.just(item) }
    }

    override fun loadCryptoListToDB(cryptoData: CryptoData): Completable {

        return local.insertCryptoDataDB(cryptoData)
                .subscribeOn(Schedulers.io())
    }

    override fun loadCryptoListFromDB(): Observable<List<CryptoData>> {

        return local.allCryptoDataDB
                .subscribeOn(Schedulers.io())
    }

    override fun deleteFavoriteFromDb(): Observable<List<CryptoData>> {

        return local.deleteAllCryptoDataFromDB()
                .subscribeOn(Schedulers.io())
                .andThen(local.allCryptoDataDB)
    }

}