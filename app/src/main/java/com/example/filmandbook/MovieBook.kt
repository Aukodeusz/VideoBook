package com.example.filmandbook

data class MovieBook(
    val title: String,
    val genre: String,
    val review: String,
    val rating: Int,
    val isMovie: Boolean,
    var isWatchedRead: Boolean = false
)

