package com.test.whtsapautoreply.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.test.whtsapautoreply.Repository.MsgRepo
import com.test.whtsapautoreply.RoomDatababse.Msgdb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MsgViewModel(private val repository: MsgRepo) :ViewModel() {


    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allItems: LiveData<List<Msgdb>> = repository.allMsg.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(dbItem: Msgdb) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.insertMsg(dbItem)
        }
    }

    fun delete(ids: List<Int>) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.delete(ids)
        }
    }


}