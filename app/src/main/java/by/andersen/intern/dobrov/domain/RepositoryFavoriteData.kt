package by.andersen.intern.dobrov.domain

import by.andersen.intern.dobrov.domain.model.pricemodel.CryptoData
import io.reactivex.Observable

interface RepositoryFavoriteData {

    fun loadCryptoListFromDB(): Observable<List<CryptoData>>
    fun deleteFavoriteFromDb(): Observable<List<CryptoData>>
}