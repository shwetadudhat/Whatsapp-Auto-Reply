package com.test.whtsapautoreply

import android.util.Log
import com.test.whtsapautoreply.RoomDatababse.MsgDao
import com.test.whtsapautoreply.RoomDatababse.Msgdb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.time.delay
import kotlinx.coroutines.withContext

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

    suspend fun refreshData() {
        // Implement your data refresh logic here
        // For example, fetch new data from a network API
        // Simulate network delay or asynchronous operation

        // Perform the data refresh operation within the IO dispatcher
        withContext(context = Dispatchers.IO) {
            try {
                // Simulate fetching the latest data from the local database
                val updatedData = msgDao.getAll() // Query all messages from the database

                // Log the updated data (for demonstration)
                Log.d("MsgLocalSource", "Refreshed data: $updatedData")

                // You can perform additional operations here if needed

            } catch (e: Exception) {
                // Handle any errors (e.g., network errors, parsing errors, etc.)
                Log.e("MsgRepo", "Error refreshing data: ${e.message}", e)
            }
        }
    }
}