package com.test.whtsapautoreply

import android.app.Application
import androidx.room.Room
import com.test.whtsapautoreply.Repository.MsgRepo
import com.test.whtsapautoreply.RoomDatababse.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApplication : Application() {
//    val msgRepository: MsgRepo
    lateinit var msgRepository: MsgRepo

    private val applicationScope = CoroutineScope(SupervisorJob())
    companion object {
        lateinit var database: AppDatabase
        lateinit var msgLocalSource: MsgLocalSource
//        lateinit var msgRepository: MsgRepo
    }


    override fun onCreate() {
        super.onCreate()
        database = AppDatabase.getDatabase(this, applicationScope)
        msgLocalSource = MsgLocalSource(database.msgDao())
        msgRepository = MsgRepo(msgLocalSource)
    }
}
