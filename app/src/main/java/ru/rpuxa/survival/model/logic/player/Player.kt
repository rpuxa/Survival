package ru.rpuxa.survival.model.logic.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.rpuxa.survival.model.logic.ConsoleMessage
import ru.rpuxa.survival.model.logic.Location
import ru.rpuxa.survival.model.logic.Location.Companion.NO_LOCATION
import ru.rpuxa.survival.random
import ru.rpuxa.survival.update

class Player(
    val id: Long,
    val slot: Int,
    val name: String,
    locationId: Int,
    exploreMessages: Iterable<ConsoleMessage>?,
    resources: Resources
) {

    val resources = ReactiveResources(resources)
    val isExploring: Boolean get() = locationId != NO_LOCATION
    val exploreMessages: LiveData<out List<ConsoleMessage>> get() = _exploreMessages
    var locationId = locationId
        private set

    private val _exploreMessages = MutableLiveData(
        ArrayList<ConsoleMessage>().apply {
            exploreMessages?.forEach { add(it) }
        }
    )

    fun startToExplore(location: Location): Resources.Needed {
        val needed = resources.sub(location.needed)
        if (needed != Resources.Needed.NOTHING)
            return needed

        locationId = location.id
        _exploreMessages.update {
            clear()
        }

        return Resources.Needed.NOTHING
    }

}