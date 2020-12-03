package by.andersen.intern.dobrov.domain.model.pricemodel

import com.google.gson.annotations.SerializedName

class USD(@field:SerializedName("price") var price: Double,
          @field:SerializedName("percent_change_24h") var percentChange24h: Double,
          @field:SerializedName("percent_change_7d") var percentChange7d: Double,
          @field:SerializedName("market_cap") var marketCap: Double)