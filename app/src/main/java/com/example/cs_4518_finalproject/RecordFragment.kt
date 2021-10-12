package com.example.cs_4518_finalproject

import android.content.Context
import android.graphics.Color
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
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
private lateinit var sound: Sound

class RecordFragment: Fragment() {

    interface Callbacks {
        fun onStartSelected()
        fun onStopSelected()
        fun onRecordCancelSelected()
        fun onRecordDoneSelected()
    }

    private var callbacks: Callbacks? = null
    private lateinit var addStartButton: Button
    private lateinit var addStopButton: Button
    private lateinit var addDoneButton: Button
    private lateinit var addCancelButton: Button

//    private val soundDetailViewModel: AddSoundDetailViewModel by lazy {
//        ViewModelProvider(this).get(AddSoundDetailViewModel::class.java)
//    }

    private var recorder: MediaRecorder? = null
    private var output: String? = null
    private var mediaRecorder: MediaRecorder? = null


    private val dir: File = File(Environment.getExternalStorageDirectory().absolutePath + "/soundrecorder/")

    private fun startRecording() {
        try{
            // create a File object for the parent directory
            val recorderDirectory = File(Environment.getExternalStorageDirectory().absolutePath+"/soundrecorder/")
            // have the object build the directory structure, if needed.
            recorderDirectory.mkdirs()
        }catch (e: IOException){
            e.printStackTrace()
        }

        if(dir.exists()){
//            output = SoundRepository.getSoundFile
        }

        mediaRecorder = MediaRecorder()

        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
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
                callbacks?.onRecordDoneSelected()
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
        fun newInstance(): RecordFragment {
            return RecordFragment()
        }
    }
}