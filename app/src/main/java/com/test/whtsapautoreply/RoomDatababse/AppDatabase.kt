package com.test.whtsapautoreply.RoomDatababse

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

abstract class AppDatabase: RoomDatabase() {
    abstract fun msgDao(): MsgDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context,
            applicationScope: CoroutineScope
        ): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "msgs_table"
                )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}