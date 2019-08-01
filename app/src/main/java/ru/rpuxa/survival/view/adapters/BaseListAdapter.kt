package ru.rpuxa.survival.view.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.rpuxa.survival.getEqualsDiff
import ru.rpuxa.survival.inflate

abstract class BaseListAdapter<T>(diff: DiffUtil.ItemCallback<T> = getEqualsDiff()) :
    ListAdapter<T, BaseListAdapter<T>.BaseViewHolder>(diff) {

    abstract inner class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun Context.bind(item: T)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        getViewHolder(parent.inflate(layout))

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        with(holder) {
            itemView.context.bind(getItem(position))
        }
    }

    @get:LayoutRes
    protected abstract val layout: Int

    protected abstract fun getViewHolder(view: View): BaseViewHolder
}