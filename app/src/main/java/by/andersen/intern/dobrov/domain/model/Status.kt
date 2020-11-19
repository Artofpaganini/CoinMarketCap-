package by.andersen.intern.dobrov.domain.model

import com.google.gson.annotations.SerializedName

class Status(@field:SerializedName("timestamp") var timestamp: String,
             @field:SerializedName("error_code") var errorCode: Int,
             @field:SerializedName("error_message") var errorMessage: String,
             @field:SerializedName("elapsed") var elapsed: Int,
             @field:SerializedName("credit_count") var creditCount: Int)