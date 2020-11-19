package by.andersen.intern.dobrov.presentation

import by.andersen.intern.dobrov.domain.model.pricemodel.CryptoData

interface OnClickAddDataInterface {
    fun addCryptoDataToFavoriteListIfClick(cryptoData: CryptoData)
}