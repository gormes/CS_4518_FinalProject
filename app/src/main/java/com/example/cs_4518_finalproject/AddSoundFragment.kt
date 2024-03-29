package com.example.cs_4518_finalproject

import android.content.Context
import android.graphics.Color
import android.os.Bundle
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

private const val ARG_SOUND_NAME = "sound_name"
private const val ARG_FILE_NAME = "file_name"
private const val TAG = "ADD SOUND FRAGMENT"
private const val NUM_ROWS = "row_num"

class AddSoundFragment: Fragment() {

    private lateinit var sound: Sound
    private lateinit var soundName: EditText
    private lateinit var soundFileName: TextView

    interface Callbacks {
        fun onAddDoneSelected()
        fun onAddCancelSelected()
        fun onRecordButSelected(soundName: String, soundId: UUID, edit:Boolean)
    }

    private var callbacks: Callbacks? = null
    private lateinit var addDoneButton: Button
    private lateinit var addCancelButton: Button
    private lateinit var addRecordButton: Button

    private val soundDetailViewModel: SoundDetailViewModel by lazy {
        ViewModelProvider(this).get(SoundDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sound=Sound()
        val rnd = Random()
        sound.colorval = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

        arguments?.let{
            sound.name = arguments?.getSerializable(ARG_SOUND_NAME) as String
            sound.filename = arguments?.getSerializable(ARG_FILE_NAME) as String
            sound.listorder = arguments?.getSerializable(NUM_ROWS) as Int
        }

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
        updateUI()
        addDoneButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                sound.name = soundName.text.toString()
                if(sound.name == ""){
                    sound.name = "Sound Effect: ${sound.id}"
                }
                soundDetailViewModel.addSound(sound)
                callbacks?.onAddDoneSelected()
            }

        })

        addRecordButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                sound.name = soundName.text.toString()
                callbacks?.onRecordButSelected(sound.name, sound.id, false)
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
        fun newInstance(soundName: String, fileName : String, numRows: Int): AddSoundFragment {
            val args = Bundle().apply {
                putSerializable(ARG_SOUND_NAME, soundName)
                putSerializable(ARG_FILE_NAME, fileName)
                putSerializable(NUM_ROWS, numRows)
            }
            return AddSoundFragment().apply {
                arguments = args
            }
        }
        fun newInstance(): AddSoundFragment {
            return AddSoundFragment()
        }
    }
}