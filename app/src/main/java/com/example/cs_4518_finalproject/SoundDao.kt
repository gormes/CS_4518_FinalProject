package com.example.cs_4518_finalproject

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface SoundDao {
    @Query("SELECT * FROM sound ORDER BY listorder")
    fun getSounds(): LiveData<List<Sound>>
    @Query("SELECT * FROM sound WHERE id=(:id)")
    fun getSound(id: UUID): LiveData<Sound>
    @Query("SELECT * FROM sound WHERE listorder=(:listorder)")
    fun getSoundByList(listorder: Int): LiveData<Sound>
    @Update
    fun updateSound(sound:Sound)
    @Insert
    fun addSound(sound: Sound)
    @Delete
    fun deleteSound(sound: Sound)

}
