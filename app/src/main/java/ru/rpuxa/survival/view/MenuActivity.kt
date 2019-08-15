package ru.rpuxa.survival.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_main_menu.*
import ru.rpuxa.survival.R
import ru.rpuxa.survival.model.database.SettingsEntity
import ru.rpuxa.survival.nnValue
import ru.rpuxa.survival.viewModel
import ru.rpuxa.survival.viewmodel.MenuViewModel

class MenuActivity : AppCompatActivity() {

    private val menuViewModel: MenuViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    override fun onResume() {
        super.onResume()
        menuViewModel.onResume()
    }
}
