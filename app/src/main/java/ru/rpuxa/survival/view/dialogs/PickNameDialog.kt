package ru.rpuxa.survival.view.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.dialog_pick_name.*
import org.jetbrains.anko.support.v4.toast
import ru.rpuxa.survival.R

class PickNameDialog : DialogFragment() {

    private var onNameListener: ((String) -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.dialog_pick_name, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        accept.setOnClickListener {
            val name = name_edit.text.toString()
            if (name.length < 4 || name.length > 16) {
                toast("Name length must be between 4 and 16")
            } else if (name.isBlank()) {
                toast("Name cannot be blank")
            } else {
                dismiss()
                onNameListener?.invoke(name)
            }
        }

        cancel.setOnClickListener {
            dismiss()
        }
    }

    fun show(manager: FragmentManager, listener: (String) -> Unit) {
        onNameListener = listener
        show(manager, PICK_NAME_DIALOG_TAG)
    }

    companion object {
        private const val PICK_NAME_DIALOG_TAG = "pick_name_dialog_tag"
    }
}