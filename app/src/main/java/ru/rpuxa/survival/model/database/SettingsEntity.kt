package ru.rpuxa.survival.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
class SettingsEntity(
    private var _lastSaveId: Long
) {

    var lastSaveId: Long
        get() {
            println("GET   ${hashCode()}   $_lastSaveId")
            return _lastSaveId
        }
        set(value) {
            println("SET    ${hashCode()}    $value")
            _lastSaveId = value
        }


    @PrimaryKey
    @JvmField
    @Deprecated("Only for database usage", level = DeprecationLevel.ERROR)
    var id: Byte = 0

    companion object {
        const val LAST_SAVE_UNDEFINED = -1L
    }
}
