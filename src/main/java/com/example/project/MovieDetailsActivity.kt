package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.flixsterplus.MOVIE_EXTRA
import com.example.flixsterplus.Movie

class MovieDetailsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val ivPoster = findViewById<ImageView>(R.id.ivPoster2)
        val tvTitle = findViewById<TextView>(R.id.tvTitle2)
        val tvOverview = findViewById<TextView>(R.id.tvOverview2)
        val tvRelease = findViewById<TextView>(R.id.tvRelease)
        val tvLang = findViewById<TextView>(R.id.tvLang)
        val tvVote = findViewById<TextView>(R.id.tvVote)

        val movie = intent.getParcelableExtra<Movie>(MOVIE_EXTRA) as Movie

        Glide.with(this)
            .load(movie.posterImageURL)
            .into(ivPoster)

        val release = movie.realease
        val lang = movie.lang
        val vote  = movie.voteAvg.toString()

        tvTitle.text = movie.title
        tvOverview.text = movie.overview
        tvRelease.text = "Release Date: $release"
        tvLang.text = "Language: $lang"
        tvVote.text  = "Average Vote $vote"
    }
}