package ru.rpuxa.survival.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import ru.rpuxa.survival.model.database.PlayersDao
import ru.rpuxa.survival.model.database.SettingsDao
import ru.rpuxa.survival.model.database.converters.toPlayer
import ru.rpuxa.survival.model.logic.player.Player
import javax.inject.Inject

class PlayerViewModel @Inject constructor(
    private val playersDao: PlayersDao,
    private val settingsDao: SettingsDao
) : ViewModel() {

    lateinit var player: Player
        private set

    fun onResume() {
        player = runBlocking {
            val settings = settingsDao.getOrNewIfAbsent()
            playersDao.getById(settings.lastSaveId)?.toPlayer() ?: error("Wrong id: ${settings.lastSaveId}")
        }
    }
}