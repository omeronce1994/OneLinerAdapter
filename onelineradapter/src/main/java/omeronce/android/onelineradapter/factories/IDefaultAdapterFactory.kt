package omeronce.android.onelineradapter.factories

import androidx.recyclerview.widget.RecyclerView
import omeronce.android.onelineradapter.adapters.OneLinerDefaultAdapter
import omeronce.android.onelineradapter.model.AdapterConfiguration
import omeronce.android.onelineradapter.viewholder.GenericViewHolder

interface IDefaultAdapterFactory {
    fun <T> create(adapterConfiguration: AdapterConfiguration<T>): OneLinerDefaultAdapter<T>
}