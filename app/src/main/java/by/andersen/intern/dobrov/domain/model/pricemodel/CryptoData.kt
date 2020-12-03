package by.andersen.intern.dobrov.domain.model.pricemodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import by.andersen.intern.dobrov.domain.model.converter.ConverterMapper
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cryptodata")
@TypeConverters(value = [ConverterMapper::class])
data class CryptoData(
        @field:SerializedName("id") @field:PrimaryKey var id: Int,
        @field:SerializedName("name") var name: String,
        @field:SerializedName("symbol") var symbol: String,
        @field:SerializedName("quote") var quote: Quote,
        var logo: String)