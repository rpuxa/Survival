package ru.rpuxa.survival.view

import android.app.Application
import ru.rpuxa.survival.dagger.Component
import ru.rpuxa.survival.dagger.DaggerComponent
import ru.rpuxa.survival.dagger.providers.ContextProvider
import ru.rpuxa.survival.dagger.providers.DataBaseProvider

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        component = DaggerComponent.builder()
            .contextProvider(ContextProvider(this))
            .dataBaseProvider(DataBaseProvider())
            .build()
    }

    companion object {
        lateinit var component: Component
            private set
    }
}