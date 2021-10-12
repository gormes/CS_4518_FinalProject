package com.example.cs_4518_finalproject

import android.content.Context
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import java.io.File
import java.io.IOException
import java.lang.IllegalStateException
import java.util.*

private const val TAG = "ADD RECORD FRAGMENT"
private const val ARG_SOUND_ID = "soundId"
private const val ARG_SOUND_NAME = "soundName"
private const val ARG_IN_PROGRESS = "inProgress"

class RecordFragment: Fragment() {

    interface Callbacks {
        fun onStartSelected()
        fun onStopSelected()
        fun onAddRecordCancelSelected()
        fun onAddRecordDoneSelected(soundName: String, fileName: String)
        fun onRecordRepeatSelected(soundName: String, soundId: UUID, inProgress: Boolean)
        fun onEditRecordCancelSelected(soundId: UUID)
        fun onEditRecordDoneSelected(fileName: String, soundId: UUID)
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

    private var fileName: String = ""
    private var soundId: UUID = UUID.randomUUID()
    private var soundName :String = ""
    private var inProgress : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            soundName = arguments?.getSerializable(ARG_SOUND_NAME) as String
            soundId = arguments?.getSerializable(ARG_SOUND_ID) as UUID
            inProgress = arguments?.getSerializable(ARG_IN_PROGRESS) as Boolean
            Log.d(TAG, "Recorder recieved: ${soundName}")
            Log.d(TAG, "Recorde recieved: ${soundId}")
        }
    }


    private fun startRecording() {
        var dir: File = File(context?.getExternalFilesDir(null)!!.absolutePath + "/soundrecordings/")
        if(dir.exists()){
            output = "${dir}"+"/${soundId}.3gp"
            if(soundName == ""){
                fileName = "${dir}"+"/${soundId}.3gp"
            } else {
                fileName = "${dir}"+"/${soundName}.3gp"
            }
        } else {
            dir.mkdir()
            output = "${dir}"+"/${soundId}.3gp"
            if(soundName == ""){
                fileName = "${dir}"+"/${soundId}.3gp"
            } else {
                fileName = "${dir}"+"/${soundName}.3gp"
            }
        }



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
        if(inProgress == false){
            addStartButton.setVisibility(View.VISIBLE)
            addStopButton.setVisibility(View.GONE)
        } else {
            addStartButton.setVisibility(View.GONE)
            addStopButton.setVisibility(View.VISIBLE)
            addCancelButton.setVisibility(View.GONE)
            addDoneButton.setVisibility(View.GONE)

        }

        addCancelButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                callbacks?.onAddRecordCancelSelected()
            }
        })


        addDoneButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var dir: File = File(context?.getExternalFilesDir(null)!!.absolutePath + "/soundrecordings/")
                if(dir.exists()){
                    if(soundName == ""){
                        fileName = "${dir}"+"/${soundId}.3gp"
                    } else {
                        fileName = "${dir}"+"/${soundName}.3gp"
                    }

                }
                callbacks?.onAddRecordDoneSelected(soundName, fileName)
            }
        })
        addStartButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                callbacks?.onRecordRepeatSelected(soundName, soundId, true)
                startRecording()
            }
        })
        addStopButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                callbacks?.onRecordRepeatSelected(soundName, soundId, false)
                stopRecording()
            }
        })
        return view
    }

    companion object {
        fun newInstance(soundName: String, soundId: UUID, inProgress: Boolean): RecordFragment {
            val args = Bundle().apply {
                putSerializable(ARG_SOUND_NAME, soundName)
                putSerializable(ARG_SOUND_ID, soundId)
                putSerializable(ARG_IN_PROGRESS, inProgress)
            }
            return RecordFragment().apply {
                arguments = args
            }
        }
        fun newInstance(): RecordFragment {
            return RecordFragment()
        }
    }

}