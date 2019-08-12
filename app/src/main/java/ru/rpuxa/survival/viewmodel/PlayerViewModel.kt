package ru.rpuxa.survival.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import ru.rpuxa.survival.model.database.PlayersDao
import ru.rpuxa.survival.model.database.SettingsDao
import ru.rpuxa.survival.model.database.SettingsEntity
import ru.rpuxa.survival.model.database.toPlayer
import ru.rpuxa.survival.model.logic.Location
import ru.rpuxa.survival.model.logic.Player
import javax.inject.Inject

class PlayerViewModel @Inject constructor(
    private val playersDao: PlayersDao,
    private val settingsDao: SettingsDao
) : ViewModel() {


    private val settings: SettingsEntity = runBlocking {
        settingsDao.getOrNewIfAbsent()
    }

    val player: Player = runBlocking {
        playersDao.getById(settings.lastSaveId)?.toPlayer() ?: error("Wrong id: ${settings.lastSaveId}")
    }

    fun startToExplore(location: Location) {

    }
}