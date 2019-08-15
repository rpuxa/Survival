package ru.rpuxa.survival.model.logic.player

import androidx.lifecycle.LiveData
import org.jetbrains.anko.doBeforeSdk
import ru.rpuxa.survival.MutableLiveData
import ru.rpuxa.survival.nnValue

class ReactiveResources(resources: Resources) : Resources {

    override val ammo get() = ammoLiveData.value!!
    override val scrap get() = scrapLiveData.value!!

    val ammoLiveData = MutableLiveData(resources.ammo)
    val scrapLiveData = MutableLiveData(resources.scrap)

    fun add(resources: Resources) {
        ammoLiveData.nnValue += resources.ammo
        scrapLiveData.nnValue += resources.scrap
    }

    fun sub(needed: Resources): Resources.Needed {
        val i = enough(needed)
        if (i != Resources.Needed.NOTHING)
            return i

        add(-needed)

        return Resources.Needed.NOTHING
    }
}