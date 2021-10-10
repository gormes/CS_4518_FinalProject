package com.example.cs_4518_finalproject;

import android.content.Context;
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.*

private const val DATABASE_NAME = "sound-database"

class SoundRepository private constructor(context:Context) {

    private val database : SoundDatabase = Room.databaseBuilder(
        context.applicationContext,
        SoundDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val soundDao = database.soundDao()
    fun getSounds(): LiveData<List<Sound>> = soundDao.getSounds()
    fun getSound(id: UUID): LiveData<Sound> = soundDao.getSound(id)

    companion object {
        private var INSTANCE: SoundRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                    INSTANCE=SoundRepository(context)
            }
        }


        fun get(): SoundRepository {
            return INSTANCE ?:
            throw IllegalStateException("SoundRepository must be initialized")
        }
    }
}