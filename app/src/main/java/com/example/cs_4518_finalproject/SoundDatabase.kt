package com.example.cs_4518_finalproject

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ Sound::class ], version=1)
@TypeConverters(SoundboardTypeConverters::class)
abstract class SoundDatabase : RoomDatabase() {
    abstract fun soundDao(): SoundDao
}