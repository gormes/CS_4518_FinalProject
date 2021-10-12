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
        fun onRecordButSelected()
        fun onRecordCancelSelected()
        fun onRecordDoneSelected()
    }

    private var callbacks: Callbacks? = null
    private lateinit var addDoneButton: Button
    private lateinit var addCancelButton: Button
    private lateinit var addRecordButton: Button

    private val soundDetailViewModel: AddSoundDetailViewModel by lazy {
        ViewModelProvider(this).get(AddSoundDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sound=Sound()
        val rnd = Random()
        sound.colorval = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        sound.filename = "No File Selected"
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
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
        soundName = view.findViewById(R.id.addSoundName)
        soundFileName = view.findViewById(R.id.addClipName)
        addDoneButton = view.findViewById(R.id.newDoneButton)
        addRecordButton = view.findViewById(R.id.addRecord)
        addDoneButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                sound.name = soundName.text.toString()
                if(sound.name == ""){
                    sound.name = "Sound Effect: ${sound.id}"
                }
                Log.i(TAG, "${sound}")
                soundDetailViewModel.addSound(sound)
                callbacks?.onAddDoneSelected()
            }

        })

        addRecordButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                callbacks?.onRecordButSelected()
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
    companion object {
        fun newInstance(): AddSoundFragment {
            return AddSoundFragment()
        }
    }
}