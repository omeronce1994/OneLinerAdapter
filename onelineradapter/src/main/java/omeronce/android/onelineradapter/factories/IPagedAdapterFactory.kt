package omeronce.android.onelineradapter.factories

import androidx.recyclerview.widget.DiffUtil
import omeronce.android.onelineradapter.adapters.OneLinerPagedAdapter
import omeronce.android.onelineradapter.model.AdapterConfiguration

interface IPagedAdapterFactory {
    fun <T> create(diffUtil: DiffUtil.ItemCallback<T>, configuration: AdapterConfiguration<T>): OneLinerPagedAdapter<T>
}