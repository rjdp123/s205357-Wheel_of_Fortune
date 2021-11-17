package com.example.s205357_wheeloffortune.fragments

import android.os.Bundle
import android.provider.ContactsContract
import android.provider.UserDictionary
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s205357_wheeloffortune.MainActivity
import com.example.s205357_wheeloffortune.R
import com.example.s205357_wheeloffortune.adapter.LetterCardAdapter
import com.example.s205357_wheeloffortune.data.DataSource

class GamePlayFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Mere læsevenlig
        val mainActivity = (activity as MainActivity)

        val view = inflater.inflate(R.layout.game_play_fragment, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.letter_recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapter = LetterCardAdapter(context, DataSource.words[mainActivity.randomWordNr])
        recyclerView.adapter = adapter

        // Viser ordkategorien
        val categoryTextView: TextView = view.findViewById(R.id.category)
        categoryTextView.text = DataSource.words[mainActivity.randomWordNr].category

        // Viser points
        val pointsTextView: TextView = view.findViewById(R.id.points)
        pointsTextView.text = "Points: " + mainActivity.points.toString()

        // Viser tilbageværende liv
        val remainingLifeTextView: TextView = view.findViewById(R.id.remaininglife)
        remainingLifeTextView.text = "Life Remaining: " + mainActivity.remaininglife.toString()


        // Testknap
        val knap = view.findViewById<Button>(R.id.buttontest)
        knap.setOnClickListener {
            // Følgende link brugt til hvordan man kalder metode fra activity i fragment
            // https://www.tutorialspoint.com/how-to-call-an-activity-method-from-a-fragment-in-android-app-using-kotlin
            mainActivity.showFragment(GamePlayFragment())

            mainActivity.newRandomWord()
            mainActivity.points++

        }
        return view
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}