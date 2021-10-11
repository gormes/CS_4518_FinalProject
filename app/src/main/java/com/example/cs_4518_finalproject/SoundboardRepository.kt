package com.example.cs_4518_finalproject

import android.content.Context
import androidx.room.Room
import java.util.*

private const val DATABASE_NAME = "soundboard-database"

class SoundboardRepository private constructor(context: Context){

    private val database : SoundboardDatabase = Room.databaseBuilder(
        context.applicationContext,
        SoundboardDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val soundboardDao = database.soundboardDAO()

    fun getSounds(): List<SoundboardEntity> = soundboardDao.getAll()

    fun getSound(id: UUID): SoundboardEntity? = soundboardDao.findByID(id)

    companion object {
        private var INSTANCE: SoundboardRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = SoundboardRepository(context)
            }
        }
        fun get(): SoundboardRepository {
            return INSTANCE ?:
            throw IllegalStateException("Soundboard Repo must be initialized")
        }
    }
}