package ru.rpuxa.survival.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_locations.*
import ru.rpuxa.survival.R
import ru.rpuxa.survival.lazyNavController
import ru.rpuxa.survival.view.adapters.LocationsAdapter
import ru.rpuxa.survival.viewModel
import ru.rpuxa.survival.viewmodel.PlayerViewModel

class LocationsFragment : Fragment() {

    private val playerViewModel: PlayerViewModel by viewModel()
    private val navController by lazyNavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (playerViewModel.player.isExploring) {
            navController.navigate(R.id.explorationFragment)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_locations, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = LocationsAdapter()
        locations_list.adapter = adapter
        locations_list.layoutManager = LinearLayoutManager(requireContext())
        locations_list.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL))

    }
}
