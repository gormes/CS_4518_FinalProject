package com.example.cs_4518_finalproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class AddSoundDetailViewModel: ViewModel() {
        private val soundRepository = SoundRepository.get()
        private val soundIdLiveData = MutableLiveData<UUID>()
        var soundLiveData: LiveData<Sound> =
            Transformations.switchMap(soundIdLiveData) { soundId ->
                soundRepository.getSound(soundId)
            }
        fun loadCrime(soundId: UUID) {
            soundIdLiveData.value = soundId
        }
}