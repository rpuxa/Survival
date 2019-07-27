package ru.rpuxa.survival.model.logic

import android.app.Dialog

interface Actions {

    suspend fun printShortMessage(msg: String)

    suspend fun setExplorePercent(percent: Int)

    suspend fun explorationFinished()
}