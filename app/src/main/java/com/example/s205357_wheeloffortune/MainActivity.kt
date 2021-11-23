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

        // Vis startskærm
        showFragment(GameStartFragment())
    }

    // Initialiserer variable til det første ordnr.
    var randomWordNr by Delegates.notNull<Int>()
    // Initialiserer variable til selve ordet opdelt i en liste
    lateinit var randomWordList: MutableList<String>

    private val REMAINING_LIFE_AT_START = 5
    var remaininglife = REMAINING_LIFE_AT_START

    private val POINTS_AT_START = 0
    var points = POINTS_AT_START

    var bankrupt = false
    var continueButton = false

    private val possibleSpins = listOf("extra", "miss", "bankrupt", "extra", "miss", "50", "100", "150", "200", "250", "300", "500", "1000", "1500", "2500")
    lateinit var spinnedString: String
    private var spinnedInt by Delegates.notNull<Int>()

    val alphabet = listOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")

    // Aktivér tryk af knapper
    var enableLetterButtons = false
    var enableSpinButton = false

    // Skal bruges til alfabetknapper
    val correctlyPressedLetters = mutableListOf<String>()
    val inCorrectlyPressedLetters = mutableListOf<String>()

    // Funktion til at skifte fragment
    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun newGame() {
        remaininglife = REMAINING_LIFE_AT_START
        points = POINTS_AT_START

        bankrupt = false

        correctlyPressedLetters.clear()
        inCorrectlyPressedLetters.clear()

        // Nyt ord:
        // Vælge random ord med offset af 1 så det passer med antallet i DataSource
        randomWordNr = (0 until (DataSource.words.size)).random()
        // Splitter ordets bogstaver i individuelle strings og lægger dem i deres egen liste
        randomWordList = DataSource.words[randomWordNr].word.split("").toMutableList()
        // Fjerner det tomme element både først og sidst i listen
        randomWordList.removeFirst()
        randomWordList.removeLast()

        enableSpinButton = true
        continueButton = false

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
            points += (spinnedInt * (randomWordList.count { it == letter }))
            val _spinnedString = getString(R.string.guessed_correctly_string, (spinnedInt * (randomWordList.count { it == letter })).toString())
            spinnedString = _spinnedString
        } else if (letter !in inCorrectlyPressedLetters) {
            inCorrectlyPressedLetters.add(letter)
            // Fjerner ét liv
            remaininglife -= 1
            val _spinnedString = getString(R.string.guessed_incorrectly_string, remaininglife.toString())
            spinnedString = _spinnedString
        }



        enableLetterButtons = false
        enableSpinButton = true
        continueButton = true
        checkLives()
    }

    // Tryk på spinknappen
    fun spinClick() {

        if (!continueButton) {
            spinnedString = possibleSpins.random()
            when (spinnedString) {
                "extra" -> {
                    val _spinnedString = getString(R.string.spin_extra_life)
                    spinnedString = _spinnedString
                    remaininglife++
                    continueButton = true
                }
                "miss" -> {
                    remaininglife -= 1
                    if (remaininglife == 0) bankrupt = true
                    continueButton = true
                    val _spinnedString = getString(R.string.spin_miss_life, remaininglife.toString())
                    spinnedString = _spinnedString
                }
                "bankrupt" -> {
                    val _spinnedString = getString(R.string.spin_bankrupt)
                    spinnedString = _spinnedString
                    bankrupt = true
                    continueButton = true
                }
                else -> {
                    spinnedInt = spinnedString.toInt()
                    val _spinnedString = getString(R.string.spin_points, spinnedString)
                    spinnedString = _spinnedString
                    enableLetterButtons = true
                    enableSpinButton = false
                }
            }
        } else if (continueButton) {
            continueButton = false
            enableSpinButton = true
            enableLetterButtons = false
        }
        checkLives()
    }

    // Funktion til at tjekke hvorvidt spillet er vundet/tabt
    private fun checkLives() {
        if ((remaininglife <= 0 || bankrupt) && !continueButton) {
            showFragment(GameLostFragment())
        } else if ((correctlyPressedLetters.containsAll(randomWordList)) && !continueButton) {
            showFragment(GameWonFragment())
        } else {
            showFragment(GamePlayFragment())
        }
    }

}