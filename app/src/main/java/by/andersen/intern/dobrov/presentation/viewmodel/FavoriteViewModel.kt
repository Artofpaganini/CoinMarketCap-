package by.andersen.intern.dobrov.presentation.viewmodel

import androidx.lifecycle.ViewModel
import by.andersen.intern.dobrov.domain.RepositoryFavoriteData
import by.andersen.intern.dobrov.domain.model.pricemodel.CryptoData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val repositoryFavoriteData: RepositoryFavoriteData) : ViewModel() {

    private val requestFavoriteCryptoList: Subject<List<CryptoData>> = BehaviorSubject.create()

    fun getRequestFavoriteCryptoList(): BehaviorSubject<List<CryptoData>> {
        getCrypto()
        return requestFavoriteCryptoList as BehaviorSubject<List<CryptoData>>
    }

    private fun getCrypto() {
        repositoryFavoriteData.loadCryptoListFromDB()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(requestFavoriteCryptoList)
    }

    fun deleteFavoriteList() {
        repositoryFavoriteData.deleteFavoriteFromDb()
                .subscribe()
    }

}