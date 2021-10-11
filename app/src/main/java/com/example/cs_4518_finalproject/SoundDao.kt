package com.example.cs_4518_finalproject

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.*

@Dao
interface SoundDao {
    @Query("SELECT * FROM sound")
    fun getSounds(): LiveData<List<Sound>>
    @Query("SELECT * FROM sound WHERE id=(:id)")
    fun getSound(id: UUID): LiveData<Sound>
    @Update
    fun updateSound(sound:Sound)
    @Insert
    fun addSound(sound: Sound)

}
