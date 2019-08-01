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
import ru.rpuxa.survival.view.App
import javax.inject.Inject

class MenuViewModel : ViewModel() {

    private val model = Model()

    private val _settings = MutableLiveData<SettingsEntity>()

    val settings: LiveData<SettingsEntity> get() = _settings
    val players = model.playersDao.getLiveDataForAll()

    fun onResume() {
        runBlocking {
            _settings.value = model.settingsDao.getOrNewIfAbsent()
        }
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
        _settings.update()
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