package ru.rpuxa.survival.model.logic

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.rpuxa.survival.R

abstract class Location {
    abstract val id: Int
    abstract val needed: Player.Resources
    abstract val events: LocationEventGroup
    abstract val name: String
    abstract val description: String
    abstract val lockReason: String
    abstract val exploreDurationMillis: Int

    fun explore(scope: CoroutineScope, player: Player, context: Context, actions: Actions) {
        scope.launch {
            val interval = context.resources.getInteger(R.integer.event_interval_millis)
            var timePassed = 0
            while (timePassed < exploreDurationMillis) {
                val percent = timePassed * 100 / exploreDurationMillis
                actions.setExplorePercent(percent)
                delay(interval.toLong())
                timePassed += interval
                events.choseRandom().perform(player, actions, context)
            }

            actions.explorationFinished()
        }
    }


}