package ru.rpuxa.survival.model.logic.player

interface Resources {
    val ammo: Long
    val scrap: Long

    fun enough(res: Resources): Needed {
        return when {
            ammo < res.ammo -> Needed.AMMO
            scrap < res.scrap -> Needed.SCRAP
            else -> Needed.NOTHING
        }
    }

    operator fun unaryMinus(): Resources = SimpleResources(
        ammo = -ammo,
        scrap = -scrap
    )


    enum class Needed {
        NOTHING,
        AMMO,
        SCRAP
    }
}