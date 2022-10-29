package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.flixsterplus.Movie
import com.example.flixsterplus.MovieAdapter
import okhttp3.Headers

private const val NOW_PLAYING_URL = "https://api.themoviedb.org/3//movie/popular/?api_key=b6d086c61784d774cf5cacd6b0f50891"

class MainActivity : AppCompatActivity() {
    private val movies = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvMovies = findViewById<RecyclerView>(R.id.rvMovies)
        val movieAdapter = MovieAdapter(this, movies)

        rvMovies.adapter = movieAdapter
        rvMovies.layoutManager = LinearLayoutManager(this)

        val client = AsyncHttpClient()
        client.get(NOW_PLAYING_URL, object : JsonHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                val movieJsonArray = json.jsonObject.getJSONArray("results")
                movies.addAll(Movie.fromJsonArray(movieJsonArray))
                movieAdapter.notifyDataSetChanged()
            }

            override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
                Log.e("MainActivity", "Failed to fetch movies :(")
            }

        })
    }
}