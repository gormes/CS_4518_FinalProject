package com.example.cs_4518_finalproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Sound(@PrimaryKey val id: UUID = UUID.randomUUID(),
                 @ColumnInfo(name = "name") var name: String = "",
                 @ColumnInfo(name = "colorval") var colorval : Int = 0,
                 @ColumnInfo(name = "filename") var filename: String = "",
                 @ColumnInfo(name = "listorder") var listorder: Int = 0) {
}


//Filename, UUID. etc