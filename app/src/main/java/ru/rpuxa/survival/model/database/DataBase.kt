package ru.rpuxa.survival.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        PlayerEntity::class,
    SettingsEntity::class
    ],
    version = 1
)
abstract class DataBase : RoomDatabase() {

    abstract val playersDao: PlayersDao
    abstract val settingsDao: SettingsDao
}