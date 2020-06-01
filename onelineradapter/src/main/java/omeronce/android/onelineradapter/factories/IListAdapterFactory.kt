package omeronce.android.onelineradapter.factories

import androidx.recyclerview.widget.DiffUtil
import omeronce.android.onelineradapter.adapters.OneLinerListAdapter
import omeronce.android.onelineradapter.model.AdapterConfiguration

interface IListAdapterFactory {
    fun <T> create(diffUtil: DiffUtil.ItemCallback<T>, configuration: AdapterConfiguration<T>): OneLinerListAdapter<T>
}