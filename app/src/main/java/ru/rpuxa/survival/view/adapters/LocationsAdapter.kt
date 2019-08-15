package ru.rpuxa.survival.view.adapters

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.item_location.view.*
import org.jetbrains.anko.toast
import ru.rpuxa.survival.R
import ru.rpuxa.survival.model.logic.Location
import ru.rpuxa.survival.model.logic.player.Player
import ru.rpuxa.survival.observe
import ru.rpuxa.survival.view.fragments.LocationsFragmentDirections
import ru.rpuxa.survival.viewmodel.PlayerViewModel

class LocationsAdapter(
    val navController: NavController,
    val owner: LifecycleOwner,
    val player: Player
) : BaseListAdapter<Location>() {



    override val layout get() = R.layout.item_location

    override fun getViewHolder(view: View) =
        object : BaseViewHolder(view) {
            val lock: View = view.location_lock
            val name: TextView = view.location_name
            val icon: ImageView = view.location_icon //TODO set icon image

            override fun Context.bind(item: Location) {
                name.text = item.name

                val canExploreLiveData = item.canExploreLiveData(owner, player)
                canExploreLiveData.observe(owner, Observer { reason ->
                    if (reason == null) {
                        lock.isVisible = false
                        view.setOnClickListener {
                            navController.navigate(LocationsFragmentDirections.actionLocationsNavToLocationDetailsDialog(item.id))
                        }
                    } else {
                        lock.isVisible = true
                        view.setOnClickListener {
                            toast(reason)
                        }
                    }
                })
            }
        }
}