package ru.rpuxa.survival.model.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.rpuxa.survival.model.logic.player.Player
import ru.rpuxa.survival.model.logic.player.SimpleResources
import ru.rpuxa.survival.random

@Entity(tableName = "players")
class PlayerEntity(
    @PrimaryKey
    val id: Long,
    val slot: Int,
    val name: String,
    val scrap: Long,
    val ammo: Long
) {

    companion object {
        fun createNew(name: String, slot: Int): PlayerEntity {
            return PlayerEntity(
                random.nextLong(),
                slot,
                name,
                0L,
                0L
            )
        }
    }
}
