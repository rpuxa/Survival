package ru.rpuxa.survival.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
class SettingsEntity(
    var lastSaveId: Long
) {

    @PrimaryKey
    @JvmField
    @Deprecated("Only for database usage")
    var id: Byte = 0

    companion object {
        const val LAST_SAVE_UNDEFINED = -1L
    }
}
