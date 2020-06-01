package omeronce.android.onelineradapter.adapters

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import omeronce.android.onelineradapter.model.AdapterConfiguration

class OneLinerAdapter {

    open class Builder<T> {

        protected val configuration =
            AdapterConfiguration<T>()

        fun withLayout(@LayoutRes layoutId: Int): Builder<T> {
            configuration.layoutId = layoutId
            return this
        }


        fun toVariableId(variableId: Int): Builder<T> {
            configuration.variableId = variableId
            return this
        }

        fun withItemClickListener(clickListener: (T) -> Unit): Builder<T> {
            configuration.clickListener = clickListener
            return this
        }

        fun setDiffUtil(diffUtil: DiffUtil.ItemCallback<T>) =
            OneLinerListAdapter.Builder(
                diffUtil, configuration
            )

        fun build() = OneLinerDefaultAdapter.Builder(configuration).build()

    }
}