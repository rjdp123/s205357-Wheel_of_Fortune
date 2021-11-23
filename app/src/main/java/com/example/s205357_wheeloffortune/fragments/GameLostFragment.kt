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

class GameLostFragment() : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.game_lost_fragment, container, false)

        if ((activity as MainActivity).bankrupt) {
            view.findViewById<TextView>(R.id.textView).text = "Well, it's just plain unfortunate to lose on a spin. Hopefully you have better luck next time"
        } else {
            view.findViewById<TextView>(R.id.textView).text = "Maybe read a dictionary so you won't guess wrong the next time"
        }

        // Replay
        val knap = view.findViewById<Button>(R.id.replay)
        knap.setOnClickListener {
            // Følgende link brugt til hvordan man kalder metode fra activity i fragment
            // https://www.tutorialspoint.com/how-to-call-an-activity-method-from-a-fragment-in-android-app-using-kotlin

            (activity as MainActivity).newGame()
        }

        return view
    }
}