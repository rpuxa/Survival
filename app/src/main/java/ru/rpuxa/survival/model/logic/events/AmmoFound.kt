package ru.rpuxa.survival.model.logic.events

import android.content.Context
import ru.rpuxa.survival.R
import ru.rpuxa.survival.model.logic.Player
import ru.rpuxa.survival.random

object AmmoFound : OnlyConsoleLocationEvent(1f) {

   private const val EXPECTED_VALUE = 100

    override fun perform(player: Player, context: Context): String {
        val value = (random.nextGaussian() * EXPECTED_VALUE).toLong()
        player.resources.value!!.add(Player.Resources(ammo = value))
        return context.getString(R.string.ammo_found, value)
    }
}