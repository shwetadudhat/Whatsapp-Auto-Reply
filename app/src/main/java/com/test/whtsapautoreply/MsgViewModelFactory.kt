package com.test.whtsapautoreply

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.whtsapautoreply.Repository.MsgRepo
import com.test.whtsapautoreply.ViewModel.MsgViewModel

class MsgViewModelFactory(private val repository: MsgRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return super.create(modelClass)
        return MsgViewModel(repository) as T
    }
}
