package com.example.cs_4518_finalproject

import Sound
import androidx.room.Dao
import androidx.room.Query
import java.util.*

@Dao
interface SoundDao {
    @Query("SELECT * FROM sound")
    fun getSounds(): List<Sound>
    @Query("SELECT * FROM sound WHERE id=(:id)")
    fun getSound(id: UUID): Sound?
}