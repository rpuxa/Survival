package ru.rpuxa.survival.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import ru.rpuxa.survival.R

class MainActivity : AppCompatActivity() {

//    private val args: MainActivityArgs by navArgs()
//    private val playerViewModel: PlayerViewModel by viewModels()
    private val navController by lazy { findNavController(R.id.content) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_view.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        alert {
            titleResource = R.string.back_dialog_title
            isCancelable = true
            positiveButton(R.string.dialog_yes) {
                navController.navigate(R.id.menuActivity)
            }
            negativeButton(R.string.dialog_no) {
                it.dismiss()
            }
        }.show()
    }
}

