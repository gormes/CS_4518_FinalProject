package com.example.cs_4518_finalproject

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = (arrayOf(SoundboardEntity::class)), version = 1)
@TypeConverters(SoundboardTypeConverters::class)
abstract class SoundboardDatabase : RoomDatabase() {
    abstract fun soundboardDAO(): SoundboardDAO
}