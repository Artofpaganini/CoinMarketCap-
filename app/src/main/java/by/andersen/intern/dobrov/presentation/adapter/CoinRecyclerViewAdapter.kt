package by.andersen.intern.dobrov.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.andersen.intern.dobrov.R
import by.andersen.intern.dobrov.domain.model.pricemodel.CryptoData
import by.andersen.intern.dobrov.presentation.OnClickAddDataInterface
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import javax.inject.Inject

class CoinRecyclerViewAdapter @Inject constructor(private val onClickAddDataInterface: OnClickAddDataInterface) : RecyclerView.Adapter<CoinRecyclerViewAdapter.MyViewHolder>() {

    private lateinit var cryptoList: List<CryptoData>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coin_recycler_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(cryptoList[position])
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    fun setCryptoList(cryptoList: List<CryptoData>) {
        this.cryptoList = cryptoList
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val coinId: TextView = itemView.findViewById(R.id.coin_id)
        private val coinName: TextView = itemView.findViewById(R.id.coin_name)
        private val coinPrice: TextView = itemView.findViewById(R.id.coin_price)
        private val coinSymbol: TextView = itemView.findViewById(R.id.coin_symbol)
        private val coinPercentChange24h: TextView = itemView.findViewById(R.id.coin_24h)
        private val coinPercentChange7d: TextView = itemView.findViewById(R.id.coin_7d)
        private val coinMarketCap: TextView = itemView.findViewById(R.id.coin_market_cap)
        private val coinImage: ImageView = itemView.findViewById(R.id.coin_image)
        private lateinit var cryptoCurrencyData: CryptoData

        private val star: ImageButton = itemView.findViewById(R.id.star)


        fun bind(cryptoCurrencyData: CryptoData) {
            this.cryptoCurrencyData = cryptoCurrencyData
            coinId.text = cryptoCurrencyData.id.toString()
            coinName.text = cryptoCurrencyData.name
            coinSymbol.text = cryptoCurrencyData.symbol
            coinPrice.text = DOLLAR + cryptoCurrencyData.quote.uSD.price

            coinPercentChange24h.text = cryptoCurrencyData.quote.uSD.percentChange24h.toString() + PERCENT
            changeColorByPosNeg24h(cryptoCurrencyData.quote.uSD.percentChange24h)

            coinPercentChange7d.text = cryptoCurrencyData.quote.uSD.percentChange7d.toString() + PERCENT
            changeColorByPosNeg7d(cryptoCurrencyData.quote.uSD.percentChange7d)

            coinMarketCap.text = DOLLAR + cryptoCurrencyData.quote.uSD.marketCap

            val requestOptions = RequestOptions()
            Glide
                    .with(itemView)
                    .load(cryptoCurrencyData.logo)
                    .apply(requestOptions)
                    .into(coinImage)
        }

        private fun changeColorByPosNeg24h(hours24: Double) {
            if ((hours24.toString() + PERCENT).contains(HYPHEN)) {
                coinPercentChange24h.setTextColor(Color.parseColor(OWN_RED))
            } else {
                coinPercentChange24h.setTextColor(Color.parseColor(OWN_GREEN))
            }
        }

        private fun changeColorByPosNeg7d(days7: Double) {
            if ((days7.toString() + PERCENT).contains(HYPHEN)) {
                coinPercentChange7d.setTextColor(Color.parseColor(OWN_RED))
            } else {
                coinPercentChange7d.setTextColor(Color.parseColor(OWN_GREEN))
            }
        }

        init {
            star.setOnClickListener {
                if (!star.isSelected) {
                    star.isSelected = true
                    onClickAddDataInterface.addCryptoDataToFavoriteListIfClick(cryptoCurrencyData)
                } else {
                    star.isSelected = false
                }
            }
        }
    }

    companion object {
        const val DOLLAR = "$ "
        const val PERCENT = " %"
        const val HYPHEN = "-"
        const val OWN_RED = "#FF0000"
        const val OWN_GREEN = "#34CD32"
    }

}