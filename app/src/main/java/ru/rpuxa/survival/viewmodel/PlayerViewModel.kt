package ru.rpuxa.survival.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import ru.rpuxa.survival.model.database.PlayersDao
import ru.rpuxa.survival.model.database.toPlayer
import ru.rpuxa.survival.model.logic.Player
import ru.rpuxa.survival.view.App
import javax.inject.Inject

class PlayerViewModel(private val playerId: Long) : ViewModel() {

    private val model = Model()

    val player: Player

    init {
        player = runBlocking {
            model.playersDao.getById(playerId) ?: error("Wrong id")
        }.toPlayer()
    }

    class Model {
        @Inject
        lateinit var playersDao: PlayersDao

        init {
            App.component.inject(this)
        }
    }
}