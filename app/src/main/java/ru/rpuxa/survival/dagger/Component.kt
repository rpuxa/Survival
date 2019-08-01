package ru.rpuxa.survival.dagger

import dagger.Component
import ru.rpuxa.survival.view.adapters.LocationsAdapter
import ru.rpuxa.survival.view.dialogs.LocationDetailsDialog
import ru.rpuxa.survival.viewmodel.MenuViewModel
import ru.rpuxa.survival.viewmodel.PlayerViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [Provider::class])
interface Component {
    fun inject(model: MenuViewModel.Model)
    fun inject(model: PlayerViewModel.Model)
    fun inject(locationsAdapter: LocationsAdapter)
    fun inject(locationDetailsDialog: LocationDetailsDialog)
}