package ru.rpuxa.survival.model.database.converters

import androidx.room.TypeConverter
import ru.rpuxa.survival.model.database.PlayerEntity
import ru.rpuxa.survival.model.logic.player.Player
import ru.rpuxa.survival.model.logic.player.SimpleResources

class PlayerConverter {
    @TypeConverter
    fun PlayerEntity.toPlayerConverter() = toPlayer()

    @TypeConverter
    fun Player.toPlayerEntityConverter() = toPlayerEntity()
}

fun PlayerEntity.toPlayer(): Player {
    val resources = SimpleResources(
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
    return PlayerEntity(
        id,
        slot,
        name,
        resources.ammo,
        resources.scrap
    )
}

