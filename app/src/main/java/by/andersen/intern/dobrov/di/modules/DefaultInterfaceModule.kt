package by.andersen.intern.dobrov.di.modules

import by.andersen.intern.dobrov.data.local.Local
import by.andersen.intern.dobrov.data.local.LocalDataImpl
import by.andersen.intern.dobrov.data.remote.Remote
import by.andersen.intern.dobrov.data.remote.RemoteDataImpl
import by.andersen.intern.dobrov.data.repository.RepositoryImpl
import by.andersen.intern.dobrov.di.scope.MyScope
import by.andersen.intern.dobrov.domain.RepositoryFavoriteData
import by.andersen.intern.dobrov.domain.RepositoryListData
import dagger.Binds
import dagger.Module

@Module
interface DefaultInterfaceModule {

    @MyScope
    @Binds
    fun provideRepositoryRemote(repository: RepositoryImpl): RepositoryListData

    @MyScope
    @Binds
    fun provideRepositoryLocal(repository: RepositoryImpl): RepositoryFavoriteData

    @MyScope
    @Binds
    fun provideRemote(remote: RemoteDataImpl): Remote

    @MyScope
    @Binds
    fun provideLocal(remote: LocalDataImpl): Local
}