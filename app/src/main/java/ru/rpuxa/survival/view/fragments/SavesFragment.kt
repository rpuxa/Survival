package ru.rpuxa.survival.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_saves.*
import ru.rpuxa.survival.R
import ru.rpuxa.survival.view.adapters.SavesAdapter
import ru.rpuxa.survival.viewmodel.MenuViewModel

class SavesFragment : Fragment() {

    val viewModel: MenuViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_saves, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = SavesAdapter()

        viewModel.players.observe(this) {

        }

        adapter.setOnNewPlayerListener { slot ->

        }

        adapter.checked.observeForever { player ->

        }



        saves_list.adapter = adapter
        saves_list.layoutManager = LinearLayoutManager(requireContext())
    }
}