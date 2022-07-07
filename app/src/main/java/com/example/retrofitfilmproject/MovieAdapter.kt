package com.example.retrofitfilmproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitfilmproject.databinding.ItemMovieBinding

class MovieAdapter(private val movieList: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {


//        fun bind(movie: Movie) {
//            binding.apply {
//                textviewDirector.text = movie.director
//                textviewReleasedate.text = movie.releaseDate
//                textviewTitle.text = movie.title
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.textviewDirector.text = movieList[position].director
        holder.binding.textviewTitle.text = movieList[position].title
        holder.binding.textviewReleasedate.text = movieList[position].release_date
    }

    override fun getItemCount(): Int {
        return movieList.size

    }


}