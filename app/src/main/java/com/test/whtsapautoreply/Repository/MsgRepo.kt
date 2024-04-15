package com.test.whtsapautoreply.Repository

import android.util.Log
import com.test.whtsapautoreply.MsgLocalSource
import com.test.whtsapautoreply.RoomDatababse.Msgdb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MsgRepo(private val msgLocalSource: MsgLocalSource) {

    //Get the data from the DB
    val allMsg: Flow<List<Msgdb>> = msgLocalSource.getAllMsg()

    //Insert the element to the DB
    fun insertMsg(msgdb: Msgdb) {
        msgLocalSource.insert(msgdb)
    }

    fun delete(ids: List<Int>) {
        msgLocalSource.delete(ids)
    }

    suspend fun refreshData() {
        msgLocalSource.refreshData()
    }

}