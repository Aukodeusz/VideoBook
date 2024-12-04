package com.example.filmandbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmandbook.R

class MovieBookAdapter(private val movieBooks: List<MovieBook>) :
    RecyclerView.Adapter<MovieBookAdapter.MovieBookViewHolder>() {

    class MovieBookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTitle: TextView = view.findViewById(R.id.textViewTitle)
        val textViewGenre: TextView = view.findViewById(R.id.textViewGenre)
        val textViewReview: TextView = view.findViewById(R.id.textViewReview)
        val textViewRating: TextView = view.findViewById(R.id.textViewRating)
        val checkBoxWatchedRead: CheckBox = view.findViewById(R.id.checkBoxWatchedRead)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieBookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_book, parent, false)
        return MovieBookViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieBookViewHolder, position: Int) {
        val movieBook = movieBooks[position]
        holder.textViewTitle.text = movieBook.title
        holder.textViewGenre.text = movieBook.genre
        holder.textViewReview.text = movieBook.review
        holder.textViewRating.text = "Recenzja: ${movieBook.rating}/10"
        holder.checkBoxWatchedRead.isChecked = movieBook.isWatchedRead
    }

    override fun getItemCount(): Int = movieBooks.size
}
