package com.example.cs_4518_finalproject

import androidx.lifecycle.ViewModel
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import java.util.*


class SoundboardListViewModel: ViewModel() {

    private val soundRepository = SoundRepository.get()
    val soundListLiveData : LiveData<List<Sound>> = soundRepository.getSounds()

    private val soundOrderLiveData = MutableLiveData<Int>()
    var soundLiveData: LiveData<Sound> =
        Transformations.switchMap(soundOrderLiveData) { listorder ->
            soundRepository.getSoundByOrder(listorder)
        }

    fun loadSoundByOrder(listorder: Int) {
        soundOrderLiveData.value = listorder
    }


    fun getSoundByOrder(listorder: Int){
        soundRepository.getSoundByOrder(listorder)
    }

    fun saveSound(sound: Sound) {
        soundRepository.updateSound(sound)
    }

}


