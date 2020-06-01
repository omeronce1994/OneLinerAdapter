package omeronce.android.onelineradapter.model

import androidx.annotation.LayoutRes

data class AdapterConfiguration<T>(
        @LayoutRes internal var layoutId: Int = 0,
        internal var variableId: Int = -1,
        internal var clickListener: ((T) -> Unit)? = null
)