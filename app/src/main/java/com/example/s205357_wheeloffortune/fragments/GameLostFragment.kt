package com.example.s205357_wheeloffortune.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.s205357_wheeloffortune.MainActivity
import com.example.s205357_wheeloffortune.R
import com.example.s205357_wheeloffortune.data.DataSource

class GameLostFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.game_lost_fragment, container, false)

        view.findViewById<TextView>(R.id.textView).text = getString(R.string.lose_text, DataSource.words[(activity as MainActivity).randomWordNr].word)

        // Replay-knap
        view.findViewById<Button>(R.id.replay).setOnClickListener {
            (activity as MainActivity).newGame()
        }

        return view
    }
}