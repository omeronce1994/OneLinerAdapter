package omeronce.android.onelineradapter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import omeronce.android.onelineradapter.factories.IDefaultAdapterFactory
import omeronce.android.onelineradapter.factories.IListAdapterFactory
import omeronce.android.onelineradapter.factories.IPagedAdapterFactory
import omeronce.android.onelineradapter.model.AdapterConfiguration
import omeronce.android.onelineradapter.viewholder.GenericViewHolder
import java.lang.IllegalStateException

open class OneLinerPagedAdapter<T> protected constructor(diffUtil: DiffUtil.ItemCallback<T>, protected val configuration: AdapterConfiguration<T>):
    PagedListAdapter<T, GenericViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(configuration.layoutId, parent, false)
        val binding: ViewDataBinding = DataBindingUtil.bind(view) ?: throw IllegalStateException("No matching layout binding found for given resources." +
                "Check if the layout is indeed wrapped using the layout tag and the layout id is correct.")
        val holder = GenericViewHolder(configuration.variableId, binding)
        setupItemClickListener(holder)
        return holder
    }


    private fun setupItemClickListener(holder: GenericViewHolder) {
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val item = getItem(position)
            item?.let{ configuration.clickListener?.invoke(it) }
        }
    }

    fun setItemClickListener(listener: (T) -> Unit) {
        configuration.clickListener = listener
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }

    class Builder<T> internal constructor(private val diffUtil: DiffUtil.ItemCallback<T>, private val configuration: AdapterConfiguration<T>) {

        @JvmOverloads
        fun build(factory: IPagedAdapterFactory = DefaultPagedAdapterFactory()) = factory.create(diffUtil, configuration)
    }

    class DefaultPagedAdapterFactory: IPagedAdapterFactory {
        override fun <T> create(
            diffUtil: DiffUtil.ItemCallback<T>,
            configuration: AdapterConfiguration<T>
        ): OneLinerPagedAdapter<T> = OneLinerPagedAdapter(diffUtil, configuration)
    }
}