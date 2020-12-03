package by.andersen.intern.dobrov.data.local

import by.andersen.intern.dobrov.domain.model.pricemodel.CryptoData
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class LocalDataImpl @Inject constructor(private val cryptoDatabase: CryptoDatabase) : Local {

    override val allCryptoDataDB: Observable<List<CryptoData>>
        get() {
            return cryptoDatabase.cryptoDAO().allCryptoData
        }

    override fun insertCryptoDataDB(cryptoData: CryptoData): Completable {

        return cryptoDatabase.cryptoDAO().insertCryptoData(cryptoData)
    }

    override fun deleteAllCryptoDataFromDB(): Completable {

        return cryptoDatabase.cryptoDAO().deleteCryptoData()
    }

}