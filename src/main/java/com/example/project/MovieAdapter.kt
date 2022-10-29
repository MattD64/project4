package com.example.flixsterplus

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project.MovieDetailsActivity
import com.example.project.R


const val MOVIE_EXTRA = "MOVIE_EXTRA"
class MovieAdapter(private val context: Context, private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inFlater = LayoutInflater.from(context)
        val movieView = inFlater.inflate(R.layout.movie_item, parent, false)
        return ViewHolder(movieView)
    }

    override fun onBindViewHolder(viewHolder: MovieAdapter.ViewHolder, position: Int) {
        val movie = movies[position]
        viewHolder.bind(movie)
    }
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie){
            tvTitle.text = movie.title
            tvOverview.text = movie.overview

            Glide.with(context)
                .load(movie.posterImageURL)
                .centerCrop()
                .into(ivPoster)
        }

        override fun onClick(p0: View?){
            val movie = movies[adapterPosition]
            //2. Use the intent system to navigate to the new activity
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_EXTRA, movie)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
      return movies.size
    }

}