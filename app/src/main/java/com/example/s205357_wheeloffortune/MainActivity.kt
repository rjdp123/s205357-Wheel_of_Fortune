package com.example.s205357_wheeloffortune

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.s205357_wheeloffortune.data.DataSource
import com.example.s205357_wheeloffortune.fragments.GameStartFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hvis startskærm
        showFragment(GameStartFragment())
    }

    // Initialiserer det første ord
    var randomWordNr = (0..((DataSource.words.size)-1)).random()


    val lifeAtStart = 5
    var remaininglife = lifeAtStart

    val pointsAtStart = 0
    var points = pointsAtStart

    // Funktion til at skifte fragment
    fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    // Nyt ord i play fragment
    fun newRandomWord() {
        // Vælge random ordnummer med offset af 1 så det passer med antallet i DataSource
        randomWordNr = (0..((DataSource.words.size)-1)).random()
    }
}