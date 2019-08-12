package ru.rpuxa.survival.dagger

import dagger.Component
import ru.rpuxa.survival.dagger.providers.ContextProvider
import ru.rpuxa.survival.dagger.providers.DataBaseProvider
import ru.rpuxa.survival.dagger.providers.LocationProvider
import ru.rpuxa.survival.dagger.providers.ViewModelProvider
import ru.rpuxa.survival.view.adapters.LocationsAdapter
import ru.rpuxa.survival.view.dialogs.LocationDetailsDialog
import ru.rpuxa.survival.viewmodel.factories.ViewModelFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ContextProvider::class,
    DataBaseProvider::class,
    LocationProvider::class,
    ViewModelProvider::class
])
interface Component {
    fun inject(model: ViewModelFactory)
    fun inject(locationsAdapter: LocationsAdapter)
    fun inject(locationDetailsDialog: LocationDetailsDialog)
}