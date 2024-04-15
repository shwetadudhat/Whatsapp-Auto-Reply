package com.test.whtsapautoreply.Repository

import com.test.whtsapautoreply.MsgLocalSource
import com.test.whtsapautoreply.RoomDatababse.Msgdb
import kotlinx.coroutines.flow.Flow

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

}