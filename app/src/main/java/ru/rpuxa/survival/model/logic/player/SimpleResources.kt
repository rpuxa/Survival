package ru.rpuxa.survival.model.logic.player

class SimpleResources(
    override val ammo: Long = 0,
    override val scrap: Long = 0
) : Resources {


    companion object {
        @JvmField
        val EMPTY = SimpleResources()
    }
}