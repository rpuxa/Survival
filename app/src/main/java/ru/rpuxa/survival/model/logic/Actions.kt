package ru.rpuxa.survival.model.logic

interface Actions {

    suspend fun printShortMessage(msg: ConsoleMessage)

    suspend fun setExplorePercent(percent: Int)

    suspend fun explorationFinished()
}