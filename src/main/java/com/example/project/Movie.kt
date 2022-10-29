package com.example.flixsterplus

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.json.JSONArray

@Parcelize

data class Movie (
    val movieId: Int,
    val posterPath: String,
    val title: String,
    val overview: String,
    val lang: String,
    val realease: String,
    val voteAvg: Double,
) : Parcelable
{

    @IgnoredOnParcel
    val posterImageURL = "https://image.tmdb.org/t/p/w342/$posterPath"
    companion object {
        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()
            for(i in 0 until movieJsonArray.length()){
                val movieJson = movieJsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                        movieJson.getInt("id"),
                        movieJson.getString("poster_path"),
                        movieJson.getString("title"),
                        movieJson.getString("overview"),
                        movieJson.getString("original_language"),
                        movieJson.getString("release_date"),
                        movieJson.getDouble("vote_average"),
                    )
                )
            }
            return movies
        }
    }
}

