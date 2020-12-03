package by.andersen.intern.dobrov.domain.model.pricemodel

import by.andersen.intern.dobrov.domain.model.Status
import com.google.gson.annotations.SerializedName

class ResponseCryptoPrice(@field:SerializedName("data") var data: List<CryptoData>,
                          @field:SerializedName("status") var status: Status)