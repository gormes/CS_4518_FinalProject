package com.example.cs_4518_finalproject

import androidx.room.*

@Database(entities = [ Sound::class ],  version=1)
@TypeConverters(SoundboardTypeConverters::class)
abstract class SoundDatabase : RoomDatabase() {
    abstract fun soundDao(): SoundDao
}