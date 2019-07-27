package ru.rpuxa.survival.model.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.rpuxa.survival.model.database.PlayerEntity
import ru.rpuxa.survival.nnValue

class Player(
    val id: Long,
    val name: String,
    resources: Resources
) {
    private val _resources = MutableLiveData(resources)

    val resources: LiveData<Resources> get() = _resources


    class Resources(
        val ammo: Long = 0L,
        val scrap: Long = 0L
    ) {

        fun enough(res: Resources): Needed {
            return when {
                ammo < res.ammo -> Needed.AMMO
                scrap < res.scrap -> Needed.SCRAP
                else -> Needed.NOTHING
            }
        }

        enum class Needed {
            NOTHING,
            AMMO,
            SCRAP
        }

        companion object {
            val FREE = Resources()
        }
    }
}