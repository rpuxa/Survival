package ru.rpuxa.survival.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.rpuxa.survival.R
import ru.rpuxa.survival.viewModel
import ru.rpuxa.survival.viewmodel.PlayerViewModel

class ExplorationFragment : Fragment() {

    val playerViewModel: PlayerViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_exploration, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}