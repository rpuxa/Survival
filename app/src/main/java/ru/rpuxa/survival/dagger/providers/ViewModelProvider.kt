package ru.rpuxa.survival.dagger.providers

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.rpuxa.survival.dagger.ViewModelKey
import ru.rpuxa.survival.viewmodel.MenuViewModel
import ru.rpuxa.survival.viewmodel.PlayerViewModel

@Module
abstract class ViewModelProvider {

    @Binds
    @IntoMap
    @ViewModelKey(PlayerViewModel::class)
    abstract fun player(v: PlayerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel::class)
    abstract fun menu(v: MenuViewModel): ViewModel
}