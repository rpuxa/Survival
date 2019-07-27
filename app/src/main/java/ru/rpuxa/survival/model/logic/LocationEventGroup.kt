package ru.rpuxa.survival.model.logic

import ru.rpuxa.survival.random

open class LocationEventGroup(private val events: List<LocationEventGroup>) {

    private val _chance: Float

    open val chance get() = _chance

    init {
        var sum = 0f
        for (event in events) {
            sum += event.chance
        }
        _chance = sum
    }

    open fun choseRandom(): LocationEvent {
        val randomNumber = (random.nextDouble() * chance).toFloat()
        var current = 0f
        for (event in events) {
            current += event.chance
            if (randomNumber <= current)
                return event.choseRandom()
        }
        error("Wrong algorithm")
    }
}