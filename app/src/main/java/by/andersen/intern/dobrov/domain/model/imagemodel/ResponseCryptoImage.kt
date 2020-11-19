package by.andersen.intern.dobrov.domain.model.imagemodel

import by.andersen.intern.dobrov.domain.model.Status
import com.google.gson.annotations.SerializedName
import java.util.*

data class ResponseCryptoImage(
        @field:SerializedName("data") var data: HashMap<String, CryptoImageData>,
        @field:SerializedName("status") var status: Status)