package ru.rpuxa.survival.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.rpuxa.survival.model.logic.Player

@Dao
abstract class PlayersDao {

    @Query("SELECT * FROM players WHERE id = :id")
    abstract suspend fun getById(id: Long): PlayerEntity?

    @Query("SELECT * FROM players")
    abstract fun getLiveDataForAll(): LiveData<List<PlayerEntity>>

    @Query("SELECT * FROM players")
    abstract suspend fun getAll(): List<PlayerEntity>

    @Insert
    abstract suspend fun insert(player: PlayerEntity)

    @Query("DELETE FROM players WHERE id = :id")
    abstract suspend fun delete(id: Long)



    suspend fun insert(player: Player) = insert(player.toPlayerEntity())
}
