package com.example.s205357_wheeloffortune.fragments

import android.os.Bundle
import android.provider.ContactsContract
import android.provider.UserDictionary
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s205357_wheeloffortune.R
import com.example.s205357_wheeloffortune.adapter.LetterCardAdapter
import com.example.s205357_wheeloffortune.data.DataSource

class GamePlayFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Vælge random ord med offset af 1
        val randomWordNr = (0..((DataSource.words.size)-1)).random()

        val view = inflater.inflate(R.layout.game_play_fragment, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.letter_recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapter = LetterCardAdapter(context, DataSource.words[randomWordNr])
        recyclerView.adapter = adapter

        val categoryTextView: TextView = view.findViewById(R.id.category) as TextView
        categoryTextView.text = DataSource.words[randomWordNr].category

        return view

    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}