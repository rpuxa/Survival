package ru.rpuxa.survival.model.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import ru.rpuxa.survival.model.logic.Player

@Dao
@TypeConverters(DataBaseConverters::class)
interface PlayersDao {

    @Query("SELECT * FROM players WHERE id = :id")
    fun getById(id: Long): Player?
}