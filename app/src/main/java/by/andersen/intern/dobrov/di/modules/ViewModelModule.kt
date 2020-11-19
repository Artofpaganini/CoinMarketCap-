package by.andersen.intern.dobrov.di.modules

import androidx.lifecycle.ViewModel
import by.andersen.intern.dobrov.di.modules.key.ViewModelKey
import by.andersen.intern.dobrov.di.scope.MyScope
import by.andersen.intern.dobrov.presentation.viewmodel.CoinListViewModel
import by.andersen.intern.dobrov.presentation.viewmodel.FavoriteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @MyScope
    @ViewModelKey(CoinListViewModel::class)
    abstract fun coinListViewModel(coinListViewModel: CoinListViewModel): ViewModel

    @Binds
    @IntoMap
    @MyScope
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun favoriteViewModel(favoriteViewModel: FavoriteViewModel): ViewModel
}