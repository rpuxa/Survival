package ru.rpuxa.survival.view.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.dialog_location_details.*
import ru.rpuxa.survival.R
import ru.rpuxa.survival.lazyNavController
import ru.rpuxa.survival.model.logic.Location
import ru.rpuxa.survival.view.App
import ru.rpuxa.survival.viewmodel.PlayerViewModel
import javax.inject.Inject

class LocationDetailsDialog : DialogFragment() {

    @Inject
    lateinit var locations: MutableSet<Location>

    private val args: LocationDetailsDialogArgs by navArgs()
    private val playerViewModel: PlayerViewModel by viewModels()
    private val navController by lazyNavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View=
        inflater.inflate(R.layout.dialog_location_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val location = locations.first { it.id == args.locationId }

        location_dialog_name.text = location.name
        location_dialog_description.text = location.description
        location_dialog_explore.setOnClickListener {
            playerViewModel.startToExplore(location)
            navController.navigate(R.id.explorationFragment)
        }
        location_dialog_back.setOnClickListener {
            navController.popBackStack()
        }
    }
}