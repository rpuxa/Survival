package ru.rpuxa.survival.model.database.converters

import androidx.room.TypeConverter
import ru.rpuxa.survival.model.logic.ConsoleMessageType

class ConsoleMessageTypeConverter {

    @TypeConverter
    fun ConsoleMessageType.toInt() = id

    @TypeConverter
    fun Int.toConsoleMessageType() =
        ConsoleMessageType.values().first { it.id == this }
}