package com.example.cs_4518_finalproject

import androidx.room.*
import java.util.*

@Dao
interface SoundboardDAO {
    @Query("Select * from soundboard_sounds")
    fun getAll(): List<SoundboardEntity>

    @Query("SELECT * FROM soundboard_sounds WHERE id=(:id)")
    fun findByID(id: UUID): SoundboardEntity

    @Insert
    fun insertAll(vararg sound: SoundboardEntity)

    @Delete
    fun delete(sound: SoundboardEntity)

    @Update
    fun updateTodo(vararg sound: SoundboardEntity)
}