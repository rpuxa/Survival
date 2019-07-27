package ru.rpuxa.survival.model.database

import androidx.room.TypeConverter
import ru.rpuxa.survival.model.logic.Player
import ru.rpuxa.survival.nnValue

class DataBaseConverters {
    @TypeConverter
    fun playerEntityToPlayer(entity: PlayerEntity): Player {
        val resources = Player.Resources(
            ammo = entity.ammo,
            scrap = entity.scrap
        )

        return Player(entity.id, entity.name, resources)
    }

    @TypeConverter
    fun playerEntityToPlayer(player: Player) {
        return with(player) {
            val resources = resources.nnValue
            PlayerEntity(
                id,
                name,
                resources.ammo,
                resources.scrap
            )
        }
    }
}