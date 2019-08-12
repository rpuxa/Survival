package ru.rpuxa.survival.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.fragment_main_menu.*
import ru.rpuxa.survival.R
import ru.rpuxa.survival.lazyNavController
import ru.rpuxa.survival.model.database.SettingsEntity
import ru.rpuxa.survival.viewModel
import ru.rpuxa.survival.viewmodel.MenuViewModel

class MainMenuFragment : Fragment() {

    private val viewModel by viewModel<MenuViewModel>()
    private val navController by lazyNavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_main_menu, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.settings.observe(this) {
            start_game.text = if (it.lastSaveId == SettingsEntity.LAST_SAVE_UNDEFINED) {
                getString(R.string.new_game)
            } else {
                getString(R.string.continue_game)
            }
        }

        start_game.setOnClickListener {
            val lastSaveId = viewModel.settings.value!!.lastSaveId
            if (lastSaveId == SettingsEntity.LAST_SAVE_UNDEFINED) {
                navController.navigate(MainMenuFragmentDirections.actionMainMenuFragmentToSavesFragment(true))
            } else {
                navController.navigate(
                    MainMenuFragmentDirections.actionMainMenuFragmentToMainActivity(lastSaveId)
                )
            }
        }

        saves.setOnClickListener {
            navController.navigate(MainMenuFragmentDirections.actionMainMenuFragmentToSavesFragment(false))
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }
}