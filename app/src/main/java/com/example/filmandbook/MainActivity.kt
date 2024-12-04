package com.example.filmandbook

/*
import MovieBook
import MovieBookAdapter
 */
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmandbook.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var movieBooks: MutableList<MovieBook>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieBookAdapter
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieBooks = mutableListOf()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MovieBookAdapter(movieBooks)
        recyclerView.adapter = adapter


        loadData()


        findViewById<Button>(R.id.buttonAdd).setOnClickListener {
            val title = findViewById<EditText>(R.id.editTextTitle).text.toString()
            val genre = findViewById<EditText>(R.id.editTextGenre).text.toString()
            val review = findViewById<EditText>(R.id.editTextReview).text.toString()
            val rating = findViewById<SeekBar>(R.id.seekBarRating).progress
            val isMovie = findViewById<RadioButton>(R.id.radioButtonMovie).isChecked

            if (title.isNotEmpty() && genre.isNotEmpty() && review.isNotEmpty()) {
                val newMovieBook = MovieBook(title, genre, review, rating, isMovie)
                movieBooks.add(newMovieBook)
                adapter.notifyDataSetChanged()

                saveData()
            }
        }
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("movieBookList", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val json = gson.toJson(movieBooks)
        editor.putString("movieBookList", json)
        editor.apply()
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("movieBookList", MODE_PRIVATE)
        val json = sharedPreferences.getString("movieBookList", null)
        if (json != null) {
            val type = object : TypeToken<MutableList<MovieBook>>() {}.type
            movieBooks = gson.fromJson(json, type)
            adapter = MovieBookAdapter(movieBooks)
            recyclerView.adapter = adapter
        }
    }
}
