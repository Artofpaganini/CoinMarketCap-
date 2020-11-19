package by.andersen.intern.dobrov.di.modules

import by.andersen.intern.dobrov.di.scope.MyScope
import by.andersen.intern.dobrov.presentation.OnClickAddDataInterface
import by.andersen.intern.dobrov.presentation.adapter.CoinRecyclerViewAdapter
import dagger.Module
import dagger.Provides

@Module
object AdapterModule {

    @MyScope
    @Provides
    fun provideRecyclerViewAdapter(onClickAddDataInterface: OnClickAddDataInterface): CoinRecyclerViewAdapter {
        return CoinRecyclerViewAdapter(onClickAddDataInterface)
    }
}