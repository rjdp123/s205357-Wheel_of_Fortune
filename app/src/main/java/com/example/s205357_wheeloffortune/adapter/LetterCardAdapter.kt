package com.example.s205357_wheeloffortune.adapter

import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s205357_wheeloffortune.R
import com.example.s205357_wheeloffortune.model.Word


// Lidt en sammenblanding af adapteren fra Dogglers-app fra Android Codelabs, prøve sig frem og https://medium.com/inside-ppl-b7/recyclerview-inside-fragment-with-android-studio-680cbed59d84
class LetterCardAdapter(
    private val context: Context?,
    private val dataset: List<Word>
): RecyclerView.Adapter<LetterCardAdapter.LetterCardViewHolder>(), ListAdapter {

    class LetterCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        val letterView: TextView = view!!.findViewById(R.id.letter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterCardViewHolder {
        return LetterCardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.letter_card, parent, false))
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: LetterCardViewHolder, position: Int) {
        val resources = context?.resources
        val item = dataset[position]
        holder.letterView.text = resources?.getString(R.string.letter, item.word)
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