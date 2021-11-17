package com.example.s205357_wheeloffortune.adapter

import android.content.Context
import android.database.DataSetObserver
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.s205357_wheeloffortune.R
import com.example.s205357_wheeloffortune.model.Word


// Lidt en sammenblanding af adapteren fra Dogglers-app fra Android Codelabs, prøve sig frem og https://medium.com/inside-ppl-b7/recyclerview-inside-fragment-with-android-studio-680cbed59d84
class LetterCardAdapter(
    private val context: Context?,
    private val dataset: Word
): RecyclerView.Adapter<LetterCardAdapter.LetterCardViewHolder>(), ListAdapter {

    class LetterCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        val letterView: TextView = view!!.findViewById(R.id.letter)
        val letterBox: RelativeLayout = view!!.findViewById(R.id.letter_filler_box)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterCardViewHolder {
        return LetterCardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.letter_card, parent, false))
    }

    // -2 da der kommer et element før og efter ordet
    override fun getItemCount(): Int = (dataset.word.split("").size)-2

    override fun onBindViewHolder(holder: LetterCardViewHolder, position: Int) {
        val resources = context?.resources

        // Opdeler ordet og ligger det i sin egen liste
        val formattedDataset = dataset.word.split("").toMutableList()
        // Der er allerede fjernet to kasser for de to tomme strings før og efter ordet.
        // Nu skal ordet bare "rykkes" på plads, ved at fjerne de to tomme elemter fra listen.
        formattedDataset.removeFirst()
        formattedDataset.removeLast()

        val item = formattedDataset[position]

        // Tydeliggør mellemrum
        if (item != " "){
            holder.letterView.text = resources?.getString(R.string.letter, item)
        } else {
            // Sætter farve. Kan ikke lige finde ud af præcis hvordan metoden virker,
            // for den tager en int, og det gør ingen forskel på farven?
            // Farven matcher kanten på de andre kasser uanset, så ikke rigtig et problem.
            holder.letterBox.setBackgroundColor(0)
        }

    }

    // Får fejl med adapterklassen hvis ikke implementeret, selvom den er uden funktion?
    override fun registerDataSetObserver(p0: DataSetObserver?) {
    }

    // Får fejl med adapterklassen hvis ikke implementeret, selvom den er uden funktion?
    override fun unregisterDataSetObserver(p0: DataSetObserver?) {
    }

    // Får fejl med adapterklassen hvis ikke implementeret, selvom den er uden funktion?
    override fun getCount(): Int {
        TODO("Også nødvendig?")
    }

    // Får fejl med adapterklassen hvis ikke implementeret, selvom den er uden funktion?
    override fun getItem(p0: Int): Any {
        TODO("Også nødvendig?")
    }

    // Får fejl med adapterklassen hvis ikke implementeret, selvom den er uden funktion?
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Også nødvendig?")
    }

    // Får fejl med adapterklassen hvis ikke implementeret, selvom den er uden funktion?
    override fun getViewTypeCount(): Int {
        TODO("Også nødvendig?")
    }

    // Får fejl med adapterklassen hvis ikke implementeret, selvom den er uden funktion?
    override fun isEmpty(): Boolean {
        TODO("Også nødvendig?")
    }

    // Får fejl med adapterklassen hvis ikke implementeret, selvom den er uden funktion?
    override fun areAllItemsEnabled(): Boolean {
        TODO("Også nødvendig?")
    }

    // Får fejl med adapterklassen hvis ikke implementeret, selvom den er uden funktion?
    override fun isEnabled(p0: Int): Boolean {
        TODO("Også nødvendig?")
    }
}