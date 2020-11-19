package by.andersen.intern.dobrov.domain.model.converter

import androidx.room.TypeConverter
import by.andersen.intern.dobrov.domain.model.pricemodel.Quote
import com.google.gson.Gson

class ConverterMapper {

    @TypeConverter
    fun fromQuote(quoteToString: Quote): String {
        return Gson().toJson(quoteToString)
    }

    @TypeConverter
    fun toQuote(stringToQuote: String): Quote {
        val gson = Gson()
        return gson.fromJson(stringToQuote, Quote::class.java)
    }
}