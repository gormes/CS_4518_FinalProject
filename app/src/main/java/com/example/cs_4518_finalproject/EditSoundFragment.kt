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

private const val ARG_SOUND_ID = "sound_id"
private const val ARG_FILE_NAME = "file_name"
private const val TAG = "EditSoundFragment"

class EditSoundFragment : Fragment() {
    private lateinit var sound: Sound
    private lateinit var soundName: EditText
    private lateinit var soundFileName: TextView

    interface Callbacks {
        fun onEditSaveSelected()
        fun onEditCancelSelected()
        fun onEditDeleteSelected()
        fun onRecordButSelected(soundName: String, soundId: UUID)
    }

    private var callbacks: Callbacks? = null
    private lateinit var editSaveButton: Button
    private lateinit var editCancelButton: Button
    private lateinit var editDeleteButton: Button
    private lateinit var editRecordButton: Button

    private var newFileName: String? = null

    private val soundDetailViewModel: AddSoundDetailViewModel by lazy {
        ViewModelProvider(this).get(AddSoundDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val soundId: UUID = arguments?.getSerializable(ARG_SOUND_ID) as UUID
        soundDetailViewModel.loadSound(soundId)
        arguments?.getSerializable(ARG_FILE_NAME).let{
            newFileName = arguments?.getSerializable(ARG_FILE_NAME) as String
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        soundDetailViewModel.soundLiveData.observe(
            viewLifecycleOwner,
            Observer { sound ->
                sound?.let {
                    this.sound = sound
                    newFileName.let{
                        this.sound.filename = newFileName!!
                        soundDetailViewModel.saveSound(this.sound)
                    }
                    updateUI()
                }
            })
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
        val view = inflater.inflate(R.layout.edit_sound, container, false)
        soundName = view.findViewById<EditText>(R.id.editSoundName)
        soundFileName = view.findViewById<TextView>(R.id.editFileSoundName)


        editDeleteButton = view.findViewById(R.id.editDeleteButton)
        editDeleteButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                soundDetailViewModel.deleteSound(sound)
                callbacks?.onEditDeleteSelected()
            }

        })

//        addDoneButton = view.findViewById(R.id.newDoneButton)
//        addDoneButton.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(view: View?) {
//                sound.name = soundName.text.toString()
//                if(sound.name == ""){
//                    sound.name = "Sound Effect: ${sound.id}"
//                }
//                //if(sound.fileAttatched == true){soundDetailViewModel.addSound(sound)}
//                soundDetailViewModel.addSound(sound)
//                callbacks?.onAddDoneSelected()
//            }
//
//        })
        editSaveButton = view.findViewById(R.id.editSaveButton)
        editSaveButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                soundDetailViewModel.saveSound(sound)
                callbacks?.onEditSaveSelected()
            }

        })
        editCancelButton = view.findViewById(R.id.editCancelButton)
        editCancelButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                callbacks?.onEditCancelSelected()
            }

        })
        editRecordButton = view.findViewById(R.id.editRecordButton)
        editRecordButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                sound.name = soundName.text.toString()
                callbacks?.onRecordButSelected(sound.name, sound.id)
            }
        })
        return view
    }

    override fun onStart() {
        super.onStart()
        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }
            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                sound.name = sequence.toString()
            }
            override fun afterTextChanged(sequence: Editable?) {
// This one too
            }
        }
        soundName.addTextChangedListener(titleWatcher)
    }
    //
    override fun onStop() {
        super.onStop()
    }

    companion object {
        fun newInstance(soundId: UUID): EditSoundFragment {
            val args = Bundle().apply {
                putSerializable(ARG_SOUND_ID, soundId)
            }
            return EditSoundFragment().apply {
                arguments = args
            }
        }
        fun newInstance(fileName: String, soundId: UUID): EditSoundFragment {
            val args = Bundle().apply {
                putSerializable(ARG_SOUND_ID, soundId)
                putSerializable(ARG_FILE_NAME, fileName)
            }
            return EditSoundFragment().apply {
                arguments = args
            }
        }
    }

}
