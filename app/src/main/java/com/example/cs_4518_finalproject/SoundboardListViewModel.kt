package com.example.cs_4518_finalproject

import Sound
import androidx.lifecycle.ViewModel
import android.graphics.Color
import java.util.*


class SoundboardListViewModel: ViewModel() {

    val sounds = mutableListOf<Sound>()
    init {
        for (i in 0 until 100) {
            val sound = Sound()
            sound.name = "Crime #$i"
            val rnd = Random()
            val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            sound.colorval = color
            sounds += sound
        }
    }

}