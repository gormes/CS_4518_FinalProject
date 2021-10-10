package com.example.cs_4518_finalproject

import androidx.lifecycle.ViewModel
import android.graphics.Color
import androidx.lifecycle.LiveData
import java.util.*


class SoundboardListViewModel: ViewModel() {

    private val soundRepository = SoundRepository.get()
    val soundListLiveData : LiveData<List<Sound>> = soundRepository.getSounds()

//    val sounds = mutableListOf<Sound>()
//    init {
//        for (i in 0 until 100) {
//            val sound = Sound()
//            sound.name = "Crime #$i"
//            val rnd = Random()
//            val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
//            sound.colorval = color
//            sounds += sound
//        }
//    }

}