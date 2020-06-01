package omeronce.android.onelineradapter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import omeronce.android.onelineradapter.factories.IDefaultAdapterFactory
import omeronce.android.onelineradapter.model.AdapterConfiguration
import omeronce.android.onelineradapter.viewholder.GenericViewHolder
import java.lang.IllegalStateException

open class OneLinerDefaultAdapter<T> protected constructor(protected val configuration: AdapterConfiguration<T>): RecyclerView.Adapter<GenericViewHolder>() {

    var items: List<T> = mutableListOf()

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
            val item = items[position]
            configuration.clickListener?.invoke(item)
        }
    }

    fun setItemClickListener(listener: (T) -> Unit) {
      configuration.clickListener = listener
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        val item = items[position]
        holder.onBind(item)
    }

    class Builder<T> internal constructor(private val configuration: AdapterConfiguration<T>) {

        @JvmOverloads
        fun build(factory: IDefaultAdapterFactory = DefaultAdapterFactory()) = factory.create(configuration)
    }

    private class DefaultAdapterFactory: IDefaultAdapterFactory {

        override fun <T> create(adapterConfiguration: AdapterConfiguration<T>): OneLinerDefaultAdapter<T> = OneLinerDefaultAdapter(adapterConfiguration)
    }
}