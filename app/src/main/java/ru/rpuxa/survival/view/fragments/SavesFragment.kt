package ru.rpuxa.survival.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_saves.*
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.toast
import ru.rpuxa.survival.R
import ru.rpuxa.survival.lazyNavController
import ru.rpuxa.survival.observe
import ru.rpuxa.survival.view.adapters.SavesAdapter
import ru.rpuxa.survival.view.dialogs.PickNameDialog
import ru.rpuxa.survival.viewModel
import ru.rpuxa.survival.viewmodel.MenuViewModel

class SavesFragment : Fragment() {

    private val menuViewModel: MenuViewModel by viewModel()
    private val navController by lazyNavController
    private val args: SavesFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_saves, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val slotsAmount = resources.getInteger(R.integer.save_slots_amount)
        val adapter = SavesAdapter(slotsAmount)

        menuViewModel.players.observe(this) { players ->
            adapter.update(
                (0 until slotsAmount).map { slot -> players.find { it.slot == slot } }
            )
        }

        adapter.setOnNewPlayerListener { slot ->
            alert {
                titleResource = R.string.create_new_game_question
                positiveButton(R.string.dialog_yes) {
                    it.dismiss()
                    newGame(slot)
                }

                negativeButton(R.string.dialog_no) {
                    it.dismiss()
                }
            }.show()
        }

        adapter.checked.observeForever { player ->
            val notNull = player != null
            begin_game.isVisible = notNull
            delete_game.isVisible = notNull
        }

        begin_game.setOnClickListener {
            startGame(adapter.checked.value!!.id)
        }

        delete_game.setOnClickListener {
            alert {
                titleResource = R.string.are_you_sure_to_delete
                positiveButton(R.string.dialog_yes) {
                    menuViewModel.deleteSave(adapter.checked.value!!.id)
                    it.dismiss()
                    toast(R.string.save_removed)
                }
                negativeButton(R.string.dialog_no) {
                    it.dismiss()
                }
            }.show()
        }

        back.setOnClickListener {
            navController.popBackStack()
        }

        saves_list.adapter = adapter
        saves_list.layoutManager = LinearLayoutManager(requireContext())
        saves_list.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        if (args.newGame) {
            newGame(0)
        }
    }

    private fun newGame(slot: Int) {
        PickNameDialog().show(childFragmentManager) { name ->
            val id = menuViewModel.newPlayer(name, slot)
            startGame(id)
        }
    }


    private fun startGame(id: Long) {
        menuViewModel.setLastSaveId(id)
        navController.navigate(SavesFragmentDirections.actionSavesFragmentToMainActivity(id))
    }

    override fun onResume() {
        super.onResume()
        menuViewModel.onResume()
    }
}
