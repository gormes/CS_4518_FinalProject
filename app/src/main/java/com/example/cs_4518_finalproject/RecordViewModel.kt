package com.example.cs_4518_finalproject

import android.annotation.SuppressLint
import android.media.MediaRecorder
import android.os.Environment
import androidx.lifecycle.ViewModel
import java.io.File
import java.io.IOException

class RecordViewModel: ViewModel() {

    private val soundRepo = SoundRepository

    fun getSoundFile(sound: Sound): File {
        return soundRepo.get().getSoundFile(sound)
    }

}