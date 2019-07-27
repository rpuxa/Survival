package ru.rpuxa.survival.view.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_save.view.*
import ru.rpuxa.survival.model.database.PlayerEntity

class SavesAdapter : RecyclerView.Adapter<SavesAdapter.SavesViewHolder>() {

    private var list: List<PlayerEntity?> = emptyList()

    class SavesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.save_name
        val checkBox: CheckBox = view.save_check_box

        fun bind(item: PlayerEntity?) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavesViewHolder {

    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: SavesViewHolder, position: Int) {
        val item = list[position]

        holder.bind(item)
    }

    fun update(list: List<PlayerEntity?>) {
        this.list = list
        notifyDataSetChanged()
    }
}