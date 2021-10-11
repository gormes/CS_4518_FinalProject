package com.example.cs_4518_finalproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class SoundDetailViewModel: ViewModel() {
        private val soundRepository = SoundRepository.get()
        private val soundIdLiveData = MutableLiveData<UUID>()
        var soundLiveData: LiveData<Sound> =
            Transformations.switchMap(soundIdLiveData) { soundId ->
                soundRepository.getSound(soundId)
            }

        fun loadSound(soundId: UUID) {
            soundIdLiveData.value = soundId
        }

        fun addSound(sound: Sound) {
            soundRepository.addSound(sound)
        }

        fun saveSound(sound: Sound) {
            soundRepository.updateSound(sound)
        }

        fun deleteSound(sound: Sound) {
            soundRepository.deleteSound(sound)
        }

}