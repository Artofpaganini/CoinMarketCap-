package by.andersen.intern.dobrov.data.remote

import by.andersen.intern.dobrov.domain.model.imagemodel.ResponseCryptoImage
import by.andersen.intern.dobrov.domain.model.pricemodel.ResponseCryptoPrice
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("cryptocurrency/listings/latest")
    fun getCryptoCurrencyList(
            @Query("CMC_PRO_API_KEY") apiKey: String): Single<ResponseCryptoPrice>

    @GET("/v1/cryptocurrency/info")
    fun getCryptoCurrencyImages(
            @Query("id") id: String,
            @Query("CMC_PRO_API_KEY") apiKey: String): Single<ResponseCryptoImage>
}