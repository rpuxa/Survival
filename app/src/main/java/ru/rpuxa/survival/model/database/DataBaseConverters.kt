package ru.rpuxa.survival.model.database

import androidx.room.TypeConverter
import ru.rpuxa.survival.model.logic.Player

class DataBaseConverters {
    @TypeConverter
    fun PlayerEntity.playerEntityToPlayer() = toPlayer()

    @TypeConverter
    fun Player.playerToPlayerEntity() = toPlayerEntity()
}

fun PlayerEntity.toPlayer(): Player {
    val resources = Player.Resources(
        ammo = ammo,
        scrap = scrap
    )

    return Player(
        id,
        slot,
        name,
        resources
    )
}

fun Player.toPlayerEntity(): PlayerEntity {
    val resources = resources.value!!
    return PlayerEntity(
        id,
        slot,
        name,
        resources.ammo,
        resources.scrap
    )
}

