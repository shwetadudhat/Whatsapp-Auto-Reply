package com.test.whtsapautoreply.RoomDatababse

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "msgs_table")
class Msgdb ( @PrimaryKey(autoGenerate = true) var id: Int? = null,
              @ColumnInfo(name = "title") val title: String,
              @ColumnInfo(name = "image_url") val imageUrl: String = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b0/Myosotis_scorpioides_-_Niitv%C3%A4lja_bog.jpg/400px-Myosotis_scorpioides_-_Niitv%C3%A4lja_bog.jpg",
              @ColumnInfo(name = "description") val description: String
)


