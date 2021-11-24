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

        // Sammenkobling af recyclerview og adapter som i Dogglers-app fra Android Codelab
        val view = inflater.inflate(R.layout.game_play_fragment, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.letter_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapter = LetterCardAdapter(context, mainActivity.randomWordList)
        recyclerView.adapter = adapter

        // Viser ordkategorien
        val categoryTextView: TextView = view.findViewById(R.id.category)
        categoryTextView.text = DataSource.words[mainActivity.randomWordNr].category

        // Viser points
        val pointsTextView: TextView = view.findViewById(R.id.points)
        pointsTextView.text = getString(R.string.points, mainActivity.points.toString())

        // Viser tilbageværende liv
        val remainingLifeTextView: TextView = view.findViewById(R.id.remaininglife)
        remainingLifeTextView.text = getString(R.string.remaining_life, mainActivity.remaininglife.toString())

        // Spinknap
        if (mainActivity.enableSpinButton) {
            if (mainActivity.continueButton) {
                view.findViewById<Button>(R.id.spinButton).text = getString(R.string.continue_button)
            } else {
                view.findViewById<Button>(R.id.spinButton).text = getString(R.string.spin_the_wheel_button)
            }
            // Hvis er spinknap aktiv, tilad da at den bruges
            view.findViewById<Button>(R.id.spinButton).setOnClickListener { mainActivity.spinClick() }
        } else {
            view.findViewById<Button>(R.id.spinButton).backgroundTintList = mainActivity.getColorStateList(R.color.grey)
        }

        // Spinresultat
        if (mainActivity.continueButton || mainActivity.enableLetterButtons) {
            view.findViewById<TextView>(R.id.spinResult).text = mainActivity.spinnedString
        } else {
            view.findViewById<TextView>(R.id.spinResult).text = ""
        }

        // Alfabetknapper
        // Til hvordan man bruger findViewById i et loop: https://stackoverflow.com/questions/4865244/android-using-findviewbyid-with-a-string-in-a-loop
        // At rykke knapperne ind i et loop lader ikke til at have været godt for performancen,
        // men det er dog langt pænere end at have 26 knapper med identisk indhold.
        for (letter in mainActivity.alphabet) {
            val buttonRef = resources.getIdentifier(("letter" + letter.uppercase()), "id", requireContext().packageName)
            val button = view.findViewById<Button>(buttonRef)

            // Hvis knap ej er blevet trykket..:
            if ((letter !in mainActivity.correctlyPressedLetters) || (letter !in mainActivity.inCorrectlyPressedLetters)) {
                // giv den da farve, hvis der må gættes...
                if (mainActivity.enableLetterButtons) {
                    button.setOnClickListener { mainActivity.letterPressed(letter) }
                // og gør den grå, hvis der ikke må
                } else if (!mainActivity.enableLetterButtons) {
                    button.backgroundTintList = mainActivity.getColorStateList(R.color.grey)
                }
            }

            // Hvis knap er blevet trykket på før, behold da farven
            if (letter in mainActivity.correctlyPressedLetters) {
                button.backgroundTintList = mainActivity.getColorStateList(R.color.green)
            }
            if (letter in mainActivity.inCorrectlyPressedLetters) {
                button.backgroundTintList = mainActivity.getColorStateList(R.color.red)
            }
        }

        return view
    }
}