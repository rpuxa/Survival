package ru.rpuxa.survival.view

import android.app.Application
import ru.rpuxa.survival.dagger.Component
import ru.rpuxa.survival.dagger.DaggerComponent
import ru.rpuxa.survival.dagger.Provider

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        component = DaggerComponent.builder()
            .provider(Provider(this))
            .build()
    }

    companion object {
        lateinit var component: Component
            private set
    }
}