package ru.rpuxa.survival.dagger.providers

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import ru.rpuxa.survival.model.logic.Location
import ru.rpuxa.survival.model.logic.locations.LakeLocation

@Module
abstract class LocationProvider {

    @Binds
    @IntoSet
    abstract fun lake(l: LakeLocation): Location
}