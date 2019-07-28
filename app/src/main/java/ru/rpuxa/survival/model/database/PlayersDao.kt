package ru.rpuxa.survival.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import ru.rpuxa.survival.model.logic.Player

@Dao
@TypeConverters
interface PlayersDao {

    @Query("SELECT * FROM players WHERE id = :id")
    suspend fun getById(id: Long): PlayerEntity?

    @Query("SELECT * FROM players")
    fun getAllEntities(): LiveData<List<PlayerEntity>>
}