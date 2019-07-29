package ru.rpuxa.survival.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import kotlinx.android.synthetic.main.activity_main.*
import ru.rpuxa.survival.R
import ru.rpuxa.survival.viewmodel.PlayerViewModel
import ru.rpuxa.survival.viewmodel.factories.PlayerViewModelFactory

class MainActivity : AppCompatActivity() {

    private val args: MainActivityArgs by navArgs()
    private val playerViewModel: PlayerViewModel by viewModels { PlayerViewModelFactory(args.playerId) }
    private val navController by lazy { findNavController(R.id.content) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        nav_view.setOnNavigationItemSelectedListener { item ->
            val current = when (item.itemId) {
                R.id.navigation_profile -> R.id.profileFragment
                R.id.navigation_locations -> R.id.stuffFragment
                R.id.navigation_stuff -> R.id.locationsFragment
                else -> error("Unknown fragment")
            }
            navController.navigate(current)
            true
        }
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle(R.string.back_dialog_title)
            .setCancelable(true)
            .setPositiveButton(R.string.dialog_yes) { _, _ ->
                navController.navigate(R.id.menuActivity)
            }
            .setNegativeButton(R.string.dialog_no) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}

