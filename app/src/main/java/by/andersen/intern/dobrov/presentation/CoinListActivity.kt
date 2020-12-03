package by.andersen.intern.dobrov.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import by.andersen.intern.dobrov.BaseApp
import by.andersen.intern.dobrov.R
import by.andersen.intern.dobrov.di.DaggerActivityComponent
import by.andersen.intern.dobrov.domain.model.pricemodel.CryptoData
import by.andersen.intern.dobrov.presentation.adapter.CoinRecyclerViewAdapter
import by.andersen.intern.dobrov.presentation.viewmodel.CoinListViewModel
import by.andersen.intern.dobrov.presentation.viewmodel.ViewModelProviderFactory
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CoinListActivity : AppCompatActivity(), OnRefreshListener, OnClickAddDataInterface {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val disposables = CompositeDisposable()
    private lateinit var recyclerView: RecyclerView
    private lateinit var coinRecyclerViewAdapter: CoinRecyclerViewAdapter
    private lateinit var coinListViewModel: CoinListViewModel

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var favoriteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity)

        initDI()
        recyclerView = findViewById(R.id.coin_recycler_view)
        swipeRefreshLayout = findViewById(R.id.swipe_to_refresh)
        favoriteButton = findViewById(R.id.favorite_button)

        coinListViewModel = ViewModelProvider(this, viewModelProviderFactory).get(CoinListViewModel::class.java)
        coinRecyclerViewAdapter = CoinRecyclerViewAdapter(this)
        swipeRefreshLayout.setOnRefreshListener(this)

        initRecyclerView()
        clickShowFavorite()
        onRefresh()
    }

    private fun showData(listCrypto: List<CryptoData>) {

        coinRecyclerViewAdapter.setCryptoList(listCrypto)
        recyclerView.adapter = coinRecyclerViewAdapter
        coinRecyclerViewAdapter.notifyDataSetChanged()
        swipeRefreshLayout.isRefreshing = false
    }

    private fun initViewModel() {
        disposables.add(coinListViewModel.getRequestCryptoList()
                .subscribe { listCrypto: List<CryptoData> -> showData(listCrypto) })
    }

    private fun initDI() {
        val activityComponent = BaseApp.appComponent?.let {
            DaggerActivityComponent
                    .builder()
                    .appComponent(it)
                    .activity(this)
                    .build()
        }
        activityComponent!!.inject(this)
    }

    private fun initRecyclerView() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@CoinListActivity)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
        Log.d(TAG, "initRecyclerView: INIT RECYCLER VIEW")
    }

    private fun onLoadingSwipeRefresh() {
        swipeRefreshLayout.post { initViewModel() }
    }

    private fun clickShowFavorite() {
        favoriteButton.setOnClickListener {
            val intent = Intent(this@CoinListActivity, FavoriteCryptoDataActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onRefresh() {
        onLoadingSwipeRefresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    override fun addCryptoDataToFavoriteListIfClick(cryptoData: CryptoData) {
        Log.d(TAG, "addCryptoDataToFavoriteListIfClick: ACTVITY")
        coinListViewModel.addFavoriteInToDb(cryptoData)
    }

    companion object {
        private const val TAG = "CoinListActivity"
    }
}