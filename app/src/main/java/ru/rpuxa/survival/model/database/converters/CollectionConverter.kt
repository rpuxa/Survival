package ru.rpuxa.survival.model.database.converters

import androidx.room.TypeConverter

class CollectionConverter {

    @TypeConverter
    fun String.toAnyArray() = toStringArray() as Array<out Any>

    @TypeConverter
    fun String.toStringArray() = split().toTypedArray()

    @TypeConverter
    fun Array<*>.convertToString() = iterator().convertToString()



    private fun String.split() = split(DIVIDER_SYMBOL)


    private fun Iterator<*>.convertToString(): String {
        val builder = StringBuilder()
        var first = true
        while (hasNext()) {
            if (first) {
                first = false
            } else {
                builder.append(DIVIDER_SYMBOL)
            }

            builder.append(next())
        }
        return builder.toString()
    }

    companion object {
       private const val DIVIDER_SYMBOL = '\u0000'
    }
}