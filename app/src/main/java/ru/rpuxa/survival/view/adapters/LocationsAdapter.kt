package ru.rpuxa.survival.view.adapters

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.item_location.view.*
import org.jetbrains.anko.toast
import ru.rpuxa.survival.R
import ru.rpuxa.survival.model.logic.Location

class LocationsAdapter : BaseListAdapter<Location>() {

    private var states: Map<Location, Boolean> = emptyMap()

    override val layout: Int get() = R.layout.item_location

    override fun getViewHolder(view: View) =
        object : BaseViewHolder(view) {
            val content: View = view.location_content
            val lock: View = view.location_lock
            val name: TextView = view.location_name

            override fun Context.bind(item: Location) {
                when (states[item] ?: States.LOCKED) {
                    States.LOCKED -> {
                        content.isVisible = false
                        lock.isVisible = true
                    }

                    States.TRANSPARENT_LOCKED -> {
                        content.isVisible = true
                        lock.isVisible = true
                        content.alpha = .3f
                    }

                    States.UNLOCKED -> {
                        content.isVisible = true
                        lock.isVisible = false
                        content.alpha = 1f
                    }
                }

                name.text = item.name
                lock.setOnClickListener {
                    toast(item.lockReason)
                }
            }
        }

    enum class States {
        UNLOCKED,
        TRANSPARENT_LOCKED,
        LOCKED
    }
}