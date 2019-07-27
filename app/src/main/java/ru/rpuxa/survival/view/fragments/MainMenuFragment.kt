package ru.rpuxa.survival.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_main_menu.*
import org.jetbrains.anko.support.v4.act
import ru.rpuxa.survival.R
import ru.rpuxa.survival.model.database.SettingsEntity
import ru.rpuxa.survival.nnValue
import ru.rpuxa.survival.viewmodel.MenuViewModel

class MainMenuFragment : Fragment() {

    private val viewModel by viewModels<MenuViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_main_menu, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController()

        viewModel.settings.observe(this) {
            start_game.text = if (it.lastSaveId == SettingsEntity.LAST_SAVE_UNDEFINED) {
                getString(R.string.new_game)
            } else {
                getString(R.string.continue_game)
            }
        }

        start_game.setOnClickListener {
            val lastSaveId = viewModel.settings.nnValue.lastSaveId
            if (lastSaveId == SettingsEntity.LAST_SAVE_UNDEFINED) {
                navController.navigate(R.id.savesFragment)
            } else {
                navController.navigate(
                    MainMenuFragmentDirections.actionMainMenuFragmentToMainActivity(lastSaveId)
                )
            }

        }
    }
}