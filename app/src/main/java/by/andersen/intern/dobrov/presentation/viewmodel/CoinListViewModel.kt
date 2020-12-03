package by.andersen.intern.dobrov.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import by.andersen.intern.dobrov.domain.RepositoryListData
import by.andersen.intern.dobrov.domain.model.pricemodel.CryptoData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

class CoinListViewModel @Inject constructor(private val repositoryListData: RepositoryListData) : ViewModel() {

    private val requestCryptoList: Subject<List<CryptoData>> = BehaviorSubject.create()

    fun getRequestCryptoList(): BehaviorSubject<List<CryptoData>> {
        getCrypto()
        return requestCryptoList as BehaviorSubject<List<CryptoData>>
    }

    @SuppressLint("CheckResult")
    private fun getCrypto() {
        repositoryListData.loadCryptoList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t: List<CryptoData> -> requestCryptoList.onNext(t) }
    }

    fun addFavoriteInToDb(cryptoData: CryptoData) {

        repositoryListData.loadCryptoListToDB(cryptoData)
                ?.subscribe()
    }

    companion object {
        private const val TAG = "CoinListViewModel"
    }
}