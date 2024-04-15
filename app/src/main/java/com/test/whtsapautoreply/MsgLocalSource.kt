package com.test.whtsapautoreply

import com.test.whtsapautoreply.RoomDatababse.MsgDao
import com.test.whtsapautoreply.RoomDatababse.Msgdb
import kotlinx.coroutines.flow.Flow

class MsgLocalSource(private val msgDao: MsgDao) {
    fun insert(msgdb: Msgdb) {
        msgDao.insertAll(msgdb)
    }

    fun getAllMsg(): Flow<List<Msgdb>> {
        return msgDao.getAll()
    }

    fun delete(ids: List<Int>) {
        msgDao.delete(ids)
    }
}