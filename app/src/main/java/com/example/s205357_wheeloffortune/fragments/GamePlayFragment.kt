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

        // Mere læsevenlig ref. til MainActivity
        val mainActivity = (activity as MainActivity)

        val view = inflater.inflate(R.layout.game_play_fragment, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.letter_recyclerview)

        // Henter ordet fra MainActivity
        val randomWordList = mainActivity.randomWordList
        // Fjerner det tomme element først og sidst i listen
        //randomWordList.removeFirst()
        //randomWordList.removeLast()


        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapter = LetterCardAdapter(context, randomWordList)
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
            mainActivity.correctlyPressedLetters.clear()

            mainActivity.showFragment(GamePlayFragment())
        }

        // Spinknap
        view.findViewById<Button>(R.id.spinButton).setOnClickListener {
            mainActivity.spinClick()
        }


        // Alfabetknapper
        // Til hvordan man bruger findViewById i et loop: https://stackoverflow.com/questions/4865244/android-using-findviewbyid-with-a-string-in-a-loop
        // At rykke knapperne ind i et loop har ikke været godt for performancen,
        // men det er dog langt pænere end at have 26 knapper med ens funktioner.
        for (i in mainActivity.alphabet) {
            val buttonRef = resources.getIdentifier(("letter" + i.uppercase()), "id", requireContext().packageName)
            val button = view.findViewById<Button>(buttonRef)


            if (mainActivity.enableLetterButtons == true) {
                // Tryk på knap
                button.setOnClickListener { mainActivity.letterPressed(i) }

                // Farve
                if (i in mainActivity.correctlyPressedLetters) {
                    button.backgroundTintList = mainActivity.getColorStateList(R.color.green)
                } else if (i in mainActivity.inCorrectlyPressedLetters) {
                    button.backgroundTintList = mainActivity.getColorStateList(R.color.red)
                }
            } else {
                button.backgroundTintList = mainActivity.getColorStateList(R.color.grey)
            }
        }

        return view
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}