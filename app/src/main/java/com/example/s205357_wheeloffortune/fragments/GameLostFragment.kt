package com.example.s205357_wheeloffortune.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.s205357_wheeloffortune.MainActivity
import com.example.s205357_wheeloffortune.R

class GameLostFragment() : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.game_lost_fragment, container, false)

        if ((activity as MainActivity).bankrupt) {
            view.findViewById<TextView>(R.id.textView).text = "Tænk engang at gå bankerot"
        } else {
            view.findViewById<TextView>(R.id.textView).text = "Det er da også uheldigt at miste sine liv"
        }

        return view
    }
}