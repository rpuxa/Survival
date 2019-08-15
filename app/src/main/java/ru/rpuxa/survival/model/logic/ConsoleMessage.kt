package ru.rpuxa.survival.model.logic

import androidx.room.TypeConverters
import ru.rpuxa.survival.model.database.converters.CollectionConverter
import ru.rpuxa.survival.model.database.converters.ConsoleMessageTypeConverter

class ConsoleMessage(
    @TypeConverters(ConsoleMessageTypeConverter::class)
    val type: ConsoleMessageType,

    @TypeConverters(CollectionConverter::class)
    vararg val args: Any
)
