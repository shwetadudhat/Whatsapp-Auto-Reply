package com.test.whtsapautoreply

import android.app.Application
import com.test.whtsapautoreply.Repository.MsgRepo
import com.test.whtsapautoreply.RoomDatababse.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    private val dataBase by lazy { AppDatabase.getDatabase(this, applicationScope) }
    private val msgLocalSource by lazy { MsgLocalSource(dataBase.msgDao()) }
    val movieRepository by lazy { MsgRepo(msgLocalSource) }
}