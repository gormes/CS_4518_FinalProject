package com.example.cs_4518_finalproject

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import java.util.*
import androidx.lifecycle.Observer

private const val TAG = "ADD SOUND FRAGMENT"
class AddSoundFragment: Fragment() {

    private lateinit var sound: Sound
    private lateinit var soundName: EditText
    private lateinit var soundFileName: TextView

    interface Callbacks {
        fun onAddDoneSelected()
        fun onAddCancelSelected()
    }

    private var callbacks: Callbacks? = null
    private lateinit var addDoneButton: Button
    private lateinit var addCancelButton: Button

    private val soundDetailViewModel: AddSoundDetailViewModel by lazy {
        ViewModelProvider(this).get(AddSoundDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sound=Sound()
        val rnd = Random()
        sound.colorval = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        soundDetailViewModel.soundLiveData.observe(
//            viewLifecycleOwner,
//            Observer { sound ->
//                sound?.let {
//                    this.sound = sound
//                    updateUI()
//                }
//            })
    }

    private fun updateUI() {
        soundName.setText(sound.name)
        soundFileName.setText(sound.filename)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.new_sound, container, false)
        soundName = view.findViewById<EditText>(R.id.addSoundName)
        addDoneButton = view.findViewById(R.id.newDoneButton)
        addDoneButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                sound.name = soundName.text.toString()
                if(sound.name == ""){
                    sound.name = "Sound Effect: ${sound.id}"
                }
                //if(sound.fileAttatched == true){soundDetailViewModel.addSound(sound)}
                soundDetailViewModel.addSound(sound)
                callbacks?.onAddDoneSelected()
            }

        })
        addCancelButton = view.findViewById(R.id.newCancelButton)
        addCancelButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                callbacks?.onAddCancelSelected()
            }

        })
        return view
    }

//    override fun onStart() {
//        super.onStart()
//        val titleWatcher = object : TextWatcher {
//            override fun beforeTextChanged(
//                sequence: CharSequence?,
//                start: Int,
//                count: Int,
//                after: Int
//            ) {
//
//            }
//            override fun onTextChanged(
//                sequence: CharSequence?,
//                start: Int,
//                before: Int,
//                count: Int
//            ) {
//                sound.name = sequence.toString()
//            }
//            override fun afterTextChanged(sequence: Editable?) {
//// This one too
//            }
//        }
//        soundName.addTextChangedListener(titleWatcher)
//    }
////
//    override fun onStop() {
//        super.onStop()
//        soundDetailViewModel.addSound(sound)
//    }


}