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

    val remaingLifeAtStart = 5
    var remaininglife = remaingLifeAtStart

    private val pointsAtStart = 0
    var points = pointsAtStart

    var bankrupt = false

    private val possibleSpins = listOf<String>("extra", "miss", "bankrupt", "50", "100", "250", "500", "1000", "1500", "2500")
    var spinnedString: String = ""
    private var spinnedInt by Delegates.notNull<Int>()

    val alphabet = listOf<String>("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")

    // Aktivér tryk af knapper
    var enableLetterButtons = false
    var enableSpinButton = false

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

    fun newGame() {
        remaininglife = remaingLifeAtStart
        points = pointsAtStart

        bankrupt = false

        correctlyPressedLetters.clear()
        inCorrectlyPressedLetters.clear()

        // Nyt ord:
        // Vælge random ord med offset af 1 så det passer med antallet i DataSource
        randomWordNr = (0..((DataSource.words.size)-1)).random()
        // Splitter ordets bogstaver i individuelle strings og lægger dem i deres egen liste
        randomWordList = DataSource.words[randomWordNr].word.split("").toMutableList()
        // Fjerner det tomme element både først og sidst i listen
        randomWordList.removeFirst()
        randomWordList.removeLast()

        enableSpinButton = true

        showFragment(GamePlayFragment())
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
            // Tilføjer point
            points = points + (spinnedInt * (randomWordList.count { it == letter }))
        } else if (letter !in inCorrectlyPressedLetters) {
            inCorrectlyPressedLetters.add(letter)
            // Fjerner ét liv
            remaininglife = remaininglife - 1
        }

        // Tjekker hvorvidt spillet skal fortsætte
        /*
        if (correctlyPressedLetters.containsAll(randomWordList)) {
            showFragment(GameWonFragment())
            enableSpinButton = true
        } else {
            showFragment(GamePlayFragment())
        }
         */

        enableLetterButtons = false
        enableSpinButton = true
        checkLives()
    }

    // Tryk på spinknappen
    fun spinClick() {
        spinnedString = possibleSpins.random()
        when (spinnedString) {
            "extra" -> {
                spinnedString = "You just got an extra life from your spin"
                remaininglife++
            }
            "miss" -> {
                spinnedString = "You just lost a life from your spin"
                remaininglife = remaininglife - 1
            }
            "bankrupt" -> {
                spinnedString = "You went bankrupt on your spin and lost all your lives"
                bankrupt = true
            }
            else -> {
                spinnedInt = spinnedString.toInt()
                enableLetterButtons = true
                enableSpinButton = false
            }
        }
        checkLives()
    }

    // Funktion til at tjekke hvorvidt spillet er vundet/tabt
    fun checkLives() {
        if (remaininglife <= 0 || bankrupt) {
            showFragment(GameLostFragment())
        } else if (correctlyPressedLetters.containsAll(randomWordList)) {
            showFragment(GameWonFragment())
        } else {
            showFragment(GamePlayFragment())
        }
    }

}