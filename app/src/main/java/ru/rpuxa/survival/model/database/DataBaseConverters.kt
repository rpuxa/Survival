package ru.rpuxa.survival.model.database

import androidx.room.TypeConverter
import ru.rpuxa.survival.model.logic.Player
import ru.rpuxa.survival.nnValue

@TypeConverter
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

@TypeConverter
fun Player.toPlayerEntity(): PlayerEntity {
    val resources = resources.nnValue
    return PlayerEntity(
        id,
        slot,
        name,
        resources.ammo,
        resources.scrap
    )
}
