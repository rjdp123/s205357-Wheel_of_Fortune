package com.example.s205357_wheeloffortune.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.s205357_wheeloffortune.MainActivity
import com.example.s205357_wheeloffortune.R

class GameStartFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.game_start_fragment, container, false)

        val knap = view.findViewById<Button>(R.id.play_game)
        knap.setOnClickListener {
            // Følgende link brugt til hvordan man kalder metode fra activity i fragment
            // https://www.tutorialspoint.com/how-to-call-an-activity-method-from-a-fragment-in-android-app-using-kotlin
            (activity as MainActivity).newRandomWord()
            (activity as MainActivity?)!!.showFragment(GamePlayFragment())
        }

        return view
    }
}
