package ru.rpuxa.survival.model.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SettingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(settings: SettingsEntity)

    @Query("SELECT * FROM settings")
    suspend fun get(): SettingsEntity?
}