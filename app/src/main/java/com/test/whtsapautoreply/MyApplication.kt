package com.test.whtsapautoreply

import android.app.Application
import com.test.whtsapautoreply.RoomDatababse.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    private val dataBase by lazy { AppDatabase.getDatabase(this, applicationScope) }
//    private val movieLocalSource by lazy { MovieLocalSource(dataBase.msgDao()) }
//    val movieRepository by lazy { MovieRepository(movieLocalSource) }
}