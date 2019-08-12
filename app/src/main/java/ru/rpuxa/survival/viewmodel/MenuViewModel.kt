package ru.rpuxa.survival.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.rpuxa.survival.model.database.PlayersDao
import ru.rpuxa.survival.model.database.SettingsDao
import ru.rpuxa.survival.model.database.SettingsEntity
import ru.rpuxa.survival.model.logic.Player
import ru.rpuxa.survival.update
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val settingsDao: SettingsDao,
    private val playersDao: PlayersDao
) : ViewModel() {

    private val _settings = MutableLiveData<SettingsEntity>()

    val settings: LiveData<SettingsEntity> get() = _settings
    val players = playersDao.getLiveDataForAll()

    fun onResume() {
        runBlocking {
            _settings.value = settingsDao.getOrNewIfAbsent()
        }
    }


    fun setLastSaveId(id: Long) {
        settings.value!!.lastSaveId = id
        updateSettings()
    }

    fun newPlayer(name: String, slot: Int): Long {
        val player = Player.create(name, slot)
        viewModelScope.launch {
            playersDao.insert(player)
        }

        return player.id
    }

    private fun updateSettings() {
        _settings.update()
        viewModelScope.launch {
            settingsDao.update(settings.value!!)
        }
    }

    fun deleteSave(id: Long) {
        viewModelScope.launch {
            playersDao.delete(id)
            val settings = settings.value!!
            if (settings.lastSaveId == id) {
                setLastSaveId(playersDao.getAll().firstOrNull()?.id ?: SettingsEntity.LAST_SAVE_UNDEFINED)
            }
        }
    }
}