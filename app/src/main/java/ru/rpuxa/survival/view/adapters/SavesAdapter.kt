package ru.rpuxa.survival.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.ViewSwitcher
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_save.view.*
import ru.rpuxa.survival.R
import ru.rpuxa.survival.model.database.PlayerEntity
import ru.rpuxa.survival.nnValue

class SavesAdapter : RecyclerView.Adapter<SavesAdapter.SavesViewHolder>() {

    private var list: List<PlayerEntity?> = emptyList()
    private val _checked = MutableLiveData<PlayerEntity?>(null)
    private var onNewPlayer: ((Int) -> Unit)? = null

    val checked: LiveData<PlayerEntity?> get() = _checked

    inner class SavesViewHolder(private val viewSwitcher: ViewSwitcher) : RecyclerView.ViewHolder(viewSwitcher) {
        private val name: TextView = viewSwitcher.save_name
        private val checkBox: CheckBox = viewSwitcher.save_check_box
        private val scrap: TextView = viewSwitcher.save_scrap
        private val ammo: TextView = viewSwitcher.save_ammo
        private val rockets: TextView = viewSwitcher.save_rockets
        private val emptySave: View = viewSwitcher.empty_save
        private val content: View = viewSwitcher.save_content

        fun bind(item: PlayerEntity?, slot: Int) {
            if ((item == null) != (viewSwitcher.currentView == emptySave)) {
                viewSwitcher.showNext()
            }
            if (item == null) {
                emptySave.setOnClickListener {
                    onNewPlayer?.invoke(slot)
                }
                return
            }

            checkBox.isChecked = item == checked.value
            name.text = item.name
            scrap.text = item.scrap.toString()
            ammo.text = item.ammo.toString()
            rockets.text = 0.toString()
            checkBox.isChecked = false

            content.setOnClickListener {
                _checked.value = if (checked.nnValue == item) null else item
                notifyDataSetChanged()
            }
        }
    }

    fun setOnNewPlayerListener(block: (Int) -> Unit) {
        onNewPlayer = block
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_save, parent, false)
        return SavesViewHolder(view as ViewSwitcher)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: SavesViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item, position)
    }

    fun update(list: List<PlayerEntity?>) {
        this.list = list
        notifyDataSetChanged()
    }
}