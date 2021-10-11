package com.example.cs_4518_finalproject

import androidx.lifecycle.ViewModel
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.LiveData
import java.util.*


class SoundboardListViewModel: ViewModel() {

    private val soundRepository = SoundRepository.get()
    val soundListLiveData : LiveData<List<Sound>> = soundRepository.getSounds()
    }

