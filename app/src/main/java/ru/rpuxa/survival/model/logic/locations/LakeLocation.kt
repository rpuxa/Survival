package ru.rpuxa.survival.model.logic.locations

import android.content.Context
import ru.rpuxa.survival.R
import ru.rpuxa.survival.model.logic.Location
import ru.rpuxa.survival.model.logic.Player
import ru.rpuxa.survival.model.logic.events.AmmoFound

class LakeLocation(context: Context) : Location() {
    override val id: Int get() = 0
    override val lockReason get() = throw UnsupportedOperationException()
    override val needed = Player.Resources.FREE
    override val events = AmmoFound
    override val name: String = context.getString(R.string.location_lake_name)
    override val description: String = context.getString(R.string.location_lake_description)
    override val exploreDurationMillis: Int = context.resources.getInteger(R.integer.location_lake_duration)
}