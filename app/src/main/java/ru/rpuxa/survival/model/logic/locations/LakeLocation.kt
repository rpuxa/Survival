package ru.rpuxa.survival.model.logic.locations

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import ru.rpuxa.survival.R
import ru.rpuxa.survival.model.logic.Location
import ru.rpuxa.survival.model.logic.player.Player
import ru.rpuxa.survival.model.logic.events.AmmoFound
import ru.rpuxa.survival.model.logic.player.SimpleResources
import ru.rpuxa.survival.nullLiveData
import javax.inject.Inject

class LakeLocation @Inject constructor(context: Context) : Location() {
    override val id: Int get() = 0
    override val needed = SimpleResources.EMPTY
    override val events = AmmoFound
    override val name: String = context.getString(R.string.location_lake_name)
    override val description: String = context.getString(R.string.location_lake_description)
    override val exploreDurationMillis: Int = context.resources.getInteger(R.integer.location_lake_duration)

    override fun canExploreLiveData(lifecycleOwner: LifecycleOwner, player: Player): LiveData<String?> =
        nullLiveData()
}