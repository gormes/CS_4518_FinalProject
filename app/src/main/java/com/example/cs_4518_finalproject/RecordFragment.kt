package com.example.cs_4518_finalproject

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import java.io.File
import java.io.IOException
import java.lang.IllegalStateException
import java.util.*

private const val TAG = "ADD RECORD FRAGMENT"
private const val ARG_SOUND = "sound"
private const val ARG_SOUND_NAME = "soundName"
private lateinit var sound: Sound

class RecordFragment: Fragment() {

    interface Callbacks {
        fun onStartSelected()
        fun onStopSelected()
        fun onRecordCancelSelected()
        fun onRecordDoneSelected(fileName:String)
    }

    private var callbacks: Callbacks? = null
    private lateinit var addStartButton: Button
    private lateinit var addStopButton: Button
    private lateinit var addDoneButton: Button
    private lateinit var addCancelButton: Button

    private val soundDetailViewModel: AddSoundDetailViewModel by lazy {
        ViewModelProvider(this).get(AddSoundDetailViewModel::class.java)
    }

    private var recorder: MediaRecorder? = null
    private var output: String? = null
    private var mediaRecorder: MediaRecorder? = null
    val recorderDirectory: File? = null

    private var fileName: String? = null
    private lateinit var soundId: UUID
    private lateinit var soundName :String

    private var dir: File = File(context?.getExternalFilesDir(null)!!.absolutePath + "/soundrecordings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val soundGet: UUID = arguments?.getSerializable(ARG_SOUND) as UUID
        soundId = soundGet
        val soundNameGet: String = arguments?.getSerializable(ARG_SOUND_NAME) as String
        soundName = soundNameGet
    }


    private fun startRecording() {
        dir.mkdir()
        output = "${dir}"+"${sound.id}.3gp"

        mediaRecorder = MediaRecorder()

        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        mediaRecorder?.setOutputFile(output)

        try {
            println("Starting recording!")
            mediaRecorder?.prepare()
            mediaRecorder?.start()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun stopRecording() {
        if (recorder != null) {
            recorder!!.release()
            recorder = null
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

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.record_sound, container, false)

        addStartButton = view.findViewById(R.id.recordStart)
        addStopButton = view.findViewById(R.id.recordStop)
        addCancelButton = view.findViewById(R.id.recordCancelButton)
        addDoneButton = view.findViewById(R.id.recordDoneButton)
        addCancelButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                callbacks?.onRecordCancelSelected()
            }
        })


        addDoneButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
//                if(fileName == null){
//                    callbacks?.onRecordDoneSelected(null)
//                } else {
//                    callbacks?.onRecordDoneSelected(fileName)
//                }
//
            }
        })
        addStartButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                callbacks?.onStartSelected()
                startRecording()
            }
        })
        addStopButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                callbacks?.onStopSelected()
                stopRecording()
            }
        })
        return view
    }

    companion object {
        fun newInstance(soundId: UUID, soundName:String): RecordFragment {
            val args = Bundle().apply {
                putSerializable(ARG_SOUND, soundId)
                putSerializable(ARG_SOUND_NAME, soundName)
            }
            return RecordFragment().apply {
                arguments = args
            }
        }
    }
}