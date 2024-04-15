package com.test.whtsapautoreply.RoomDatababse

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface MsgDao {

    @Insert
    fun insertAll(vararg msgItems: Msgdb)

    @Query("SELECT * FROM msgs_table")
    fun getAll(): Flow<List<Msgdb>>
    @Query("DELETE FROM msgs_table WHERE id IN (:ids)")
     fun delete(ids: List<Int>)
}