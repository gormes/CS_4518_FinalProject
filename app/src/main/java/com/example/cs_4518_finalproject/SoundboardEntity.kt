package com.example.cs_4518_finalproject

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "soundboard_sounds")
data class SoundboardEntity(
    @PrimaryKey var id: UUID = UUID.randomUUID(),
    var soundName: String = "",
    var date: Date = Date(),
)