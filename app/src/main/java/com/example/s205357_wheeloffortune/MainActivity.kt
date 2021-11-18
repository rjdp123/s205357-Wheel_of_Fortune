package com.example.s205357_wheeloffortune

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.s205357_wheeloffortune.data.DataSource
import com.example.s205357_wheeloffortune.fragments.GameLostFragment
import com.example.s205357_wheeloffortune.fragments.GamePlayFragment
import com.example.s205357_wheeloffortune.fragments.GameStartFragment
import com.example.s205357_wheeloffortune.fragments.GameWonFragment
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hvis startskærm
        showFragment(GameStartFragment())
    }

    // Initialiserer variable til det første ordnr.
    var randomWordNr by Delegates.notNull<Int>()
    // Initialiserer variable til selve ordet opdelt i en liste
    lateinit var randomWordList: MutableList<String>

    val lifeAtStart = 5
    var remaininglife = lifeAtStart

    val pointsAtStart = 0
    var points = pointsAtStart

    // Skal bruges til alfabetknapper
    val correctlyPressedLetters = mutableListOf<String>()
    val inCorrectlyPressedLetters = mutableListOf<String>()

    // Funktion til at skifte fragment
    fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    // Nyt ord i play fragment
    fun newRandomWord() {
        // Vælge random ord med offset af 1 så det passer med antallet i DataSource
        randomWordNr = (0..((DataSource.words.size)-1)).random()
        // Splitter ordets bogstaver i individuelle strings og lægger dem i deres egen liste
        randomWordList = DataSource.words[randomWordNr].word.split("").toMutableList()
        // Fjerner det tomme element både først og sidst i listen
        randomWordList.removeFirst()
        randomWordList.removeLast()
    }





    // Udvælger tilfældigt ord, opdeler og læggger det i sin egen liste
    //val randomWordList = DataSource.words[randomWordNr].word.split("").toMutableList()
    // Fjerner det tomme elemt først og sidst i listen

    // Tryk på alfabetknapper
    fun letterPressed(letter: String) {

        // Tilføjer mellemrum, da det ikke skal gættes på
        if ((" ") in randomWordList && (" ") !in correctlyPressedLetters) {
            correctlyPressedLetters.add(" ")
        }

        // Tjekker om bogstavet indgår i ordet
        if (letter in randomWordList && letter !in correctlyPressedLetters) {
            correctlyPressedLetters.add(letter)
        } else if (letter !in inCorrectlyPressedLetters) {
            inCorrectlyPressedLetters.add(letter)
        }

        // Tjekker hvorvidt spillet skal fortsætte
        if (correctlyPressedLetters.containsAll(randomWordList)) {
            showFragment(GameWonFragment())
            println("JA DEN ER GOD NOK!!!!")
            println(correctlyPressedLetters)
            println(randomWordList)
            println("JA DEN ER GOD NOK!!!!")
        } else {
            showFragment(GamePlayFragment())
            println("JA DEN ER WRONG!!!!")
            println(correctlyPressedLetters)
            println(randomWordList)
            println("JA DEN ER WRONG!!!!")
        }
    }
}