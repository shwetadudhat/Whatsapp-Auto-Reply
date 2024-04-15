package com.test.whtsapautoreply.RoomDatababse

import androidx.room.Dao
import androidx.room.Insert


@Dao
interface MsgDao {

    @Insert
    fun insertAll(vararg msgItems: Msgdb)
}