package com.example.cs_4518_finalproject

import androidx.room.*

@Database(entities = [ Sound::class ],   autoMigrations = [
    AutoMigration (from = 1, to = 2)
], version=2)
@TypeConverters(SoundboardTypeConverters::class)
abstract class SoundDatabase : RoomDatabase() {
    abstract fun soundDao(): SoundDao
}