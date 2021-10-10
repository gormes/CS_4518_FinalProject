package com.example.cs_4518_finalproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


private const val TAG = "SoundboardListFragment"

class SoundboardListFragment : Fragment() {

    private lateinit var soundboardRecyclerView: RecyclerView
    private var adapter: SoundAdapter? = SoundAdapter(emptyList())

    private val soundboardListViewModel: SoundboardListViewModel by lazy {
        ViewModelProvider(this).get(SoundboardListViewModel::class.java)
    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.d(TAG, "Total crimes: ${soundboardListViewModel.sounds.size}")
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_soundboard, container, false)
        soundboardRecyclerView =
            view.findViewById(R.id.recycler_view) as RecyclerView
        soundboardRecyclerView.layoutManager = LinearLayoutManager(context)
        soundboardRecyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        soundboardListViewModel.soundListLiveData.observe(
            viewLifecycleOwner,
            Observer { sounds ->
                sounds?.let {
                    Log.i(TAG, "Got crimes ${sounds.size}")
                    updateUI(sounds)
                }
            })
    }

    private inner class SoundHolder(view: View)
        : RecyclerView.ViewHolder(view) {
        private lateinit var sound: Sound
        private val nameTextView: TextView = itemView.findViewById(R.id.soundName)
        private val colorTextView: TextView = itemView.findViewById(R.id.color)

        fun bind(sound: Sound) {
            this.sound = sound
            nameTextView.text = this.sound.name
            var col = 0
            this.sound.colorval?.let {col = it}
            colorTextView.setBackgroundColor(col)
        }

    }

    private inner class SoundAdapter(var sounds: List<Sound>)
        : RecyclerView.Adapter<SoundHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : SoundHolder {
            val view = layoutInflater.inflate(R.layout.sound_item, parent, false)
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
        fun newInstance(): SoundboardListFragment {
            return SoundboardListFragment()
        }
    }
}