package ru.rpuxa.survival.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
class PlayerEntity(
    @PrimaryKey
    val id: Long,
    val slot: Int,
    val name: String,
    val scrap: Long,
    val ammo: Long
) {
}