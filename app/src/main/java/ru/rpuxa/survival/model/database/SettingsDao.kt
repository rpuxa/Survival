package ru.rpuxa.survival.model.database

import androidx.room.*

@Dao
abstract class SettingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(settings: SettingsEntity)

    @Query("SELECT * FROM settings")
    protected abstract suspend fun get(): SettingsEntity?

    @Transaction
    open suspend fun getOrNewIfAbsent(): SettingsEntity {
        val settings = get()
        if (settings != null)
            return settings
        val newSettings = SettingsEntity.createNew()
        update(newSettings)
        return newSettings
    }
}