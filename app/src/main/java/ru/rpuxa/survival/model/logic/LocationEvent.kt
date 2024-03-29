package ru.rpuxa.survival.model.logic

import android.content.Context
import ru.rpuxa.survival.model.logic.player.Player

abstract class LocationEvent(
    override val chance: Float
) : LocationEventGroup(emptyList()) {
    override fun choseRandom(): LocationEvent = this

    abstract suspend fun perform(
        player: Player,
        actions: Actions,
        context: Context
    )
}