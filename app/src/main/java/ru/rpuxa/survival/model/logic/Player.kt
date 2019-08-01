package ru.rpuxa.survival.model.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.rpuxa.survival.random

class Player(
    val id: Long,
    val slot: Int,
    val name: String,
    resources: Resources
) {
    private val _resources = MutableLiveData(resources)

    val resources: LiveData<Resources> get() = _resources

    val isExploring: Boolean get() = false


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

        fun add(resources: Resources) {

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

    companion object {
        fun create(name: String, slot: Int): Player {
            return Player(
                random.nextLong(),
                slot,
                name,
                Resources()
            )
        }
    }
}