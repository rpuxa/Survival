package ru.rpuxa.survival.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SettingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(settings: SettingsEntity)

    @Query("SELECT * FROM settings")
    fun get(): LiveData<SettingsEntity>
}