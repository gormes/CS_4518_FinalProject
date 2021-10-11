package com.example.cs_4518_finalproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

private const val TAG2 = "EditSoundboardListFrag"

class EditSoundboardListFragment : Fragment() {

    interface Callbacks {
        fun onBackSelected()
        fun onSoundSelected(soundId: UUID)
    }

    private var callbacks: Callbacks? = null

    private lateinit var doneEditButton: Button

    private lateinit var soundboardRecyclerView: RecyclerView
    private var adapter: SoundAdapter? = SoundAdapter(emptyList())

    private val soundboardListViewModel: SoundboardListViewModel by lazy {
        ViewModelProvider(this).get(SoundboardListViewModel::class.java)
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.edit_soundboard, container, false)
        soundboardRecyclerView =
            view.findViewById(R.id.edit_recycler_view) as RecyclerView
        soundboardRecyclerView.layoutManager = LinearLayoutManager(context)
        doneEditButton = view.findViewById(R.id.doneButton)
        doneEditButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                callbacks?.onBackSelected()
            }

        })
        soundboardRecyclerView.adapter = adapter

        return view
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
        soundboardListViewModel.soundListLiveData.observe(
            viewLifecycleOwner,
            Observer { sounds ->
                sounds?.let {
                    Log.i(TAG2, "Got crimes 2${sounds.size}")
                    updateUI(sounds)
                }
            })


    }


    private inner class SoundHolder(view: View)
        : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var sound: Sound
        private val nameTextView: TextView = itemView.findViewById(R.id.soundName)
        private val colorTextView: TextView = itemView.findViewById(R.id.color)
        private val upArrow: AppCompatImageButton = itemView.findViewById(R.id.upArrow)
        private val downArrow: AppCompatImageButton = itemView.findViewById(R.id.downArrow)
        init {
            itemView.setOnClickListener(this)
            upArrow.setOnClickListener(){
                Log.i(TAG2, "Up Clicked")
            }
            downArrow.setOnClickListener(){
                Log.i(TAG2, "DownClicked")
            }
        }

        fun bind(sound: Sound) {
            this.sound = sound
            nameTextView.text = this.sound.name
            var col = 0
            this.sound.colorval?.let {col = it}
            colorTextView.setBackgroundColor(col)
        }

        override fun onClick(v: View?) {
            callbacks?.onSoundSelected(sound.id)
        }



    }

    private inner class SoundAdapter(var sounds: List<Sound>)
        : RecyclerView.Adapter<SoundHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : SoundHolder {
            val view = layoutInflater.inflate(R.layout.edit_sound_item, parent, false)
            return SoundHolder(view)
        }
        override fun getItemCount() = sounds.size
        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            val sound = sounds[position]
            holder.bind(sound)
        }
    }
    private fun updateUI(sounds : List<Sound>) {
        adapter = SoundAdapter(sounds)
        soundboardRecyclerView.adapter = adapter
    }

    companion object {
        fun newInstance(): EditSoundboardListFragment {
            return EditSoundboardListFragment()
        }
    }

}