package ru.rpuxa.survival.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.rpuxa.survival.model.database.PlayerEntity
import ru.rpuxa.survival.model.database.PlayersDao
import ru.rpuxa.survival.model.database.SettingsDao
import ru.rpuxa.survival.model.database.SettingsEntity
import ru.rpuxa.survival.model.logic.Player
import ru.rpuxa.survival.view.App
import javax.inject.Inject

class MenuViewModel : ViewModel() {

    private val model = Model()

    lateinit var settings: LiveData<SettingsEntity>
        private set
    lateinit var players: LiveData<List<PlayerEntity>>
        private set

    init {
        settings = model.settingsDao.get()
        if (settings.value == null) {
            val newSettings = SettingsEntity(SettingsEntity.LAST_SAVE_UNDEFINED)
            viewModelScope.launch {
                model.settingsDao.update(newSettings)
            }
        }
        players = model.playersDao.getLiveDataForAll()
    }


    fun setLastSaveId(id: Long) {
        settings.value!!.lastSaveId = id
        updateSettings()
    }

    fun newPlayer(name: String, slot: Int): Long {
        val player = Player.create(name, slot)
        viewModelScope.launch {
            model.playersDao.insert(player)
        }

        return player.id
    }

    private fun updateSettings() {
        viewModelScope.launch {
            model.settingsDao.update(settings.value!!)
        }
    }

    fun deleteSave(id: Long) {
        viewModelScope.launch {
            model.playersDao.delete(id)
            val settings = settings.value!!
            if (settings.lastSaveId == id) {
                setLastSaveId(model.playersDao.getAll().firstOrNull()?.id ?: SettingsEntity.LAST_SAVE_UNDEFINED)
            }
        }
    }

    class Model {
        @Inject
        lateinit var settingsDao: SettingsDao

        @Inject
        lateinit var playersDao: PlayersDao

        init {
            App.component.inject(this)
        }
    }
}