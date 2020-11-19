package by.andersen.intern.dobrov

import android.app.Application
import by.andersen.intern.dobrov.di.AppComponent
import by.andersen.intern.dobrov.di.DaggerAppComponent


class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build()
    }

    companion object {
        var appComponent: AppComponent? = null
            private set
    }

}