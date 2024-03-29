package ru.rpuxa.survival.model.logic.events

import android.content.Context
import ru.rpuxa.survival.model.logic.Actions
import ru.rpuxa.survival.model.logic.ConsoleMessage
import ru.rpuxa.survival.model.logic.LocationEvent
import ru.rpuxa.survival.model.logic.player.Player

abstract class OnlyConsoleLocationEvent(chance: Float) : LocationEvent(chance) {

    abstract fun perform(player: Player, context: Context): ConsoleMessage

    final override suspend fun perform(
        player: Player,
        actions: Actions,
        context: Context
    ) {
        val msg = perform(player, context)
        actions.printShortMessage(msg)
    }
}