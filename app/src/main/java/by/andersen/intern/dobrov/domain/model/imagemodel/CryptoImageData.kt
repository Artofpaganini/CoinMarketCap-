package by.andersen.intern.dobrov.domain.model.imagemodel

import com.google.gson.annotations.SerializedName

data class CryptoImageData(@field:SerializedName("id") var id: Int,
                           @field:SerializedName("logo") var logo: String)