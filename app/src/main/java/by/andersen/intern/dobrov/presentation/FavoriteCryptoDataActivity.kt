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
import by.andersen.intern.dobrov.BaseApp
import by.andersen.intern.dobrov.R
import by.andersen.intern.dobrov.di.DaggerActivityComponent
import by.andersen.intern.dobrov.domain.model.pricemodel.CryptoData

import by.andersen.intern.dobrov.presentation.adapter.FavoriteRecyclerViewAdapter
import by.andersen.intern.dobrov.presentation.viewmodel.FavoriteViewModel
import by.andersen.intern.dobrov.presentation.viewmodel.ViewModelProviderFactory
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class FavoriteCryptoDataActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val disposables = CompositeDisposable()
    private lateinit var recyclerView: RecyclerView
    private lateinit var favoriteRecyclerViewAdapter: FavoriteRecyclerViewAdapter
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var backToListBtn: Button
    private lateinit var deleteAllPosBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorite_activity)

        recyclerView = findViewById(R.id.coin_recycler_view)
        favoriteRecyclerViewAdapter = FavoriteRecyclerViewAdapter()
        backToListBtn = findViewById(R.id.back_to_list_button)
        deleteAllPosBtn = findViewById(R.id.delete_all_positions)

        initDI()
        initRecyclerView()

        favoriteViewModel = ViewModelProvider(this, viewModelProviderFactory).get(FavoriteViewModel::class.java)

        clickBackToList()
        clickDeleteAllPositions()
        initViewModel()
    }

    private fun initRecyclerView() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@FavoriteCryptoDataActivity)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
    }

    private fun showData(listCrypto: List<CryptoData>) {
        favoriteRecyclerViewAdapter.setCryptoList(listCrypto)
        recyclerView.adapter = favoriteRecyclerViewAdapter
        favoriteRecyclerViewAdapter.notifyDataSetChanged()
    }


    private fun initViewModel() {
        disposables.add(favoriteViewModel.getRequestFavoriteCryptoList()
                .subscribe { listCrypto: List<CryptoData> -> showData(listCrypto) })
    }

    private fun clickBackToList() {
        backToListBtn.setOnClickListener {
            val intent = Intent(this@FavoriteCryptoDataActivity, CoinListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun clickDeleteAllPositions() {
        deleteAllPosBtn.setOnClickListener {

            favoriteViewModel.deleteFavoriteList()
        }
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

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

}