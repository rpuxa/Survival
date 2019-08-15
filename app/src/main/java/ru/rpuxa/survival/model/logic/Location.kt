package ru.rpuxa.survival.model.logic

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.rpuxa.survival.R
import ru.rpuxa.survival.model.logic.player.Player
import ru.rpuxa.survival.model.logic.player.Resources

abstract class Location {
    abstract val id: Int
    abstract val needed: Resources
    abstract val events: LocationEventGroup
    abstract val name: String
    abstract val description: String
    abstract val exploreDurationMillis: Int

    /**
     * @return reason why cannot or null if can
     */
    abstract fun canExploreLiveData(lifecycleOwner: LifecycleOwner, player: Player): LiveData<String?>

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


    companion object {
        const val NO_LOCATION = -1
    }
}