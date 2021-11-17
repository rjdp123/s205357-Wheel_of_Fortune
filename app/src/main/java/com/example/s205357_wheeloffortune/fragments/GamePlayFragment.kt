package com.example.s205357_wheeloffortune.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s205357_wheeloffortune.R
import com.example.s205357_wheeloffortune.adapter.LetterCardAdapter
import com.example.s205357_wheeloffortune.data.DataSource

class GamePlayFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.game_play_fragment, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.letter_recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapter = LetterCardAdapter(context, DataSource.words)
        recyclerView.adapter = adapter

        return view

    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}