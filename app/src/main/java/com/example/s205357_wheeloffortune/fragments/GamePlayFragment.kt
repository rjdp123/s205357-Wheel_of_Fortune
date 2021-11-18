package com.example.s205357_wheeloffortune.fragments

import android.os.Bundle
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

            mainActivity.newRandomWord()
            mainActivity.points++
            mainActivity.pressedLetterList.clear()

            mainActivity.showFragment(GamePlayFragment())
        }

        // Spinknap
        view.findViewById<Button>(R.id.spinButton).setOnClickListener {

        }


        // Alfabetknapper
        view.findViewById<Button>(R.id.letterA).setOnClickListener {
            mainActivity.letterPressed("a")
        }
        view.findViewById<Button>(R.id.letterB).setOnClickListener {
            mainActivity.letterPressed("b")
        }
        view.findViewById<Button>(R.id.letterC).setOnClickListener {
            mainActivity.letterPressed("c")
        }
        view.findViewById<Button>(R.id.letterD).setOnClickListener {
            mainActivity.letterPressed("d")
        }
        view.findViewById<Button>(R.id.letterE).setOnClickListener {
            mainActivity.letterPressed("e")
        }
        view.findViewById<Button>(R.id.letterF).setOnClickListener {
            mainActivity.letterPressed("f")
        }
        view.findViewById<Button>(R.id.letterG).setOnClickListener {
            mainActivity.letterPressed("g")
        }
        view.findViewById<Button>(R.id.letterH).setOnClickListener {
            mainActivity.letterPressed("h")
        }
        view.findViewById<Button>(R.id.letterI).setOnClickListener {
            mainActivity.letterPressed("i")
        }
        view.findViewById<Button>(R.id.letterJ).setOnClickListener {
            mainActivity.letterPressed("j")
        }
        view.findViewById<Button>(R.id.letterK).setOnClickListener {
            mainActivity.letterPressed("k")
        }
        view.findViewById<Button>(R.id.letterL).setOnClickListener {
            mainActivity.letterPressed("l")
        }
        view.findViewById<Button>(R.id.letterM).setOnClickListener {
            mainActivity.letterPressed("m")
        }
        view.findViewById<Button>(R.id.letterN).setOnClickListener {
            mainActivity.letterPressed("n")
        }
        view.findViewById<Button>(R.id.letterO).setOnClickListener {
            mainActivity.letterPressed("o")
        }
        view.findViewById<Button>(R.id.letterP).setOnClickListener {
            mainActivity.letterPressed("p")
        }
        view.findViewById<Button>(R.id.letterQ).setOnClickListener {
            mainActivity.letterPressed("q")
        }
        view.findViewById<Button>(R.id.letterR).setOnClickListener {
            mainActivity.letterPressed("r")
        }
        view.findViewById<Button>(R.id.letterS).setOnClickListener {
            mainActivity.letterPressed("s")
        }
        view.findViewById<Button>(R.id.letterT).setOnClickListener {
            mainActivity.letterPressed("t")
        }
        view.findViewById<Button>(R.id.letterU).setOnClickListener {
            mainActivity.letterPressed("u")
        }
        view.findViewById<Button>(R.id.letterV).setOnClickListener {
            mainActivity.letterPressed("v")
        }
        view.findViewById<Button>(R.id.letterW).setOnClickListener {
            mainActivity.letterPressed("w")
        }
        view.findViewById<Button>(R.id.letterX).setOnClickListener {
            mainActivity.letterPressed("x")
        }
        view.findViewById<Button>(R.id.letterY).setOnClickListener {
            mainActivity.letterPressed("y")
        }
        view.findViewById<Button>(R.id.letterZ).setOnClickListener {
            mainActivity.letterPressed("z")
        }


        return view
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}