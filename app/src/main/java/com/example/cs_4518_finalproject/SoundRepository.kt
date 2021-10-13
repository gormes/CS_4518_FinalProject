package com.example.cs_4518_finalproject;

import android.annotation.SuppressLint
import android.content.Context;
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.io.File
import java.io.IOException
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "sound-database7"

class SoundRepository private constructor(context:Context) {

    private val executor = Executors.newSingleThreadExecutor()
    private val filesDir = context.applicationContext.filesDir

    fun getSoundFile(sound: Sound): File = File(filesDir, sound.filename)

    private val database : SoundDatabase = Room.databaseBuilder(
        context.applicationContext,
        SoundDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val soundDao = database.soundDao()

    fun getSounds(): LiveData<List<Sound>> = soundDao.getSounds()
    fun getSound(id: UUID): LiveData<Sound> = soundDao.getSound(id)
    fun getSoundByOrder(listorder: Int): LiveData<Sound> = soundDao.getSoundByList(listorder)
    fun getNum(): Int {
        var i = 0
        executor.execute{
            i = soundDao.getNum()
        }
        return i
    }

    fun updateSound(sound: Sound) {
        executor.execute {

        }
    }
    fun addSound(sound: Sound) {
        executor.execute {
            soundDao.addSound(sound)
        }
    }
    fun deleteSound(sound: Sound) {
        executor.execute {
            soundDao.deleteSound(sound)
        }
    }


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