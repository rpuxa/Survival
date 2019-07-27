package ru.rpuxa.survival.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.rpuxa.survival.model.database.PlayersDao
import ru.rpuxa.survival.model.database.SettingsDao
import ru.rpuxa.survival.model.database.SettingsEntity
import ru.rpuxa.survival.nnValue
import ru.rpuxa.survival.view.App
import javax.inject.Inject

class MenuViewModel : ViewModel() {

    private val model = Model()

    private val _settings: MutableLiveData<SettingsEntity>
    val settings: LiveData<SettingsEntity> get() = _settings

    init {
        _settings = runBlocking {
            val dbSettings = model.settingsDao.get()
            val settings = if (dbSettings != null) {
                dbSettings
            } else {
                val newSettings = SettingsEntity(SettingsEntity.LAST_SAVE_UNDEFINED)
                model.settingsDao.update(newSettings)
                newSettings
            }
            MutableLiveData(settings)
        }
    }


    fun setLastSaveId(id: Long) {
        settings.nnValue.lastSaveId = id
        updateInDataBase()
    }

    private fun updateInDataBase() {
        viewModelScope.launch {
            model.settingsDao.update(settings.nnValue)
        }
    }

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        viewModelScope.cancel()
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