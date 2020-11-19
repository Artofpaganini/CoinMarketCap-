package by.andersen.intern.dobrov.di.modules

import android.app.Application
import by.andersen.intern.dobrov.data.local.CryptoDAO
import by.andersen.intern.dobrov.data.local.CryptoDatabase
import by.andersen.intern.dobrov.data.local.CryptoDatabase.Companion.getInstance
import by.andersen.intern.dobrov.di.scope.MyScope
import dagger.Module
import dagger.Provides

@Module
object LocalModule {

    @MyScope
    @Provides
    fun provideGetInstance(application: Application): CryptoDatabase {
        return getInstance(application)
    }

    @MyScope
    @Provides
    fun provideNewsDAO(cryptoDatabase: CryptoDatabase): CryptoDAO {
        return cryptoDatabase.cryptoDAO()
    }
}