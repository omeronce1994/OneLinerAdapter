package omeronce.android.onelineradapter.view.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import omeronce.android.onelineradapter.utils.SingleLiveEvent

open class BaseViewModel: ViewModel() {

    protected val _showLoading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }
    val showLoading: LiveData<Boolean> = _showLoading

    val snackBarEvent = SingleLiveEvent<String>()
}