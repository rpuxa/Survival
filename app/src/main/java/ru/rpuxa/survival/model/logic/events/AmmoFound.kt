package ru.rpuxa.survival.model.logic.events

import android.content.Context
import ru.rpuxa.survival.model.logic.ConsoleMessageType
import ru.rpuxa.survival.model.logic.ConsoleMessage
import ru.rpuxa.survival.model.logic.player.Player
import ru.rpuxa.survival.model.logic.player.SimpleResources
import ru.rpuxa.survival.random

object AmmoFound : OnlyConsoleLocationEvent(1f) {

   private const val EXPECTED_VALUE = 100

    override fun perform(player: Player, context: Context): ConsoleMessage {
        val value = (random.nextGaussian() * EXPECTED_VALUE).toLong()
        player.resources.add(SimpleResources(ammo = value))
        return ConsoleMessage(
            ConsoleMessageType.AMMO_FOUND,
            value
        )
    }
}