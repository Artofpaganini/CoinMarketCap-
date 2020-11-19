package by.andersen.intern.dobrov.di

import android.app.Activity
import by.andersen.intern.dobrov.di.modules.*
import by.andersen.intern.dobrov.di.scope.MyScope
import by.andersen.intern.dobrov.presentation.CoinListActivity
import by.andersen.intern.dobrov.presentation.FavoriteCryptoDataActivity
import dagger.BindsInstance
import dagger.Component

@MyScope
@Component(dependencies = [AppComponent::class],
        modules = [ViewModelModule::class, DefaultInterfaceModule::class,
            RemoteModule::class, LocalModule::class, AdapterModule::class])

interface ActivityComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun activity(activity: Activity): Builder
        fun appComponent(appComponent: AppComponent): Builder
        fun build(): ActivityComponent
    }

    fun inject(coinListActivity: CoinListActivity)
    fun inject(favoriteCryptoDataActivity: FavoriteCryptoDataActivity)
}