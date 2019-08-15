package ru.rpuxa.survival.model.logic

import androidx.annotation.StringRes
import ru.rpuxa.survival.R

enum class ConsoleMessageType(val id: Int, @StringRes val msg: Int) {
    AMMO_FOUND(0, R.string.ammo_found)
}