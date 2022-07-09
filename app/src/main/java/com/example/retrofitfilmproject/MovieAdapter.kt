package com.example.retrofitfilmproject

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitfilmproject.databinding.ItemMovieBinding

class MovieAdapter(
    private val movieList: MutableList<Movie>,
    private val listener: OnItemClickListener

) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                println(position)
                Log.e("dwad", position.toString())
                if (position != RecyclerView.NO_POSITION) {
                    val item = movieList.get(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }

            }
        }


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

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.textviewDirector.text = movieList[position].director
        holder.binding.textviewTitle.text = movieList[position].title
        holder.binding.textviewReleasedate.text = movieList[position].release_date
        holder.itemView.setOnClickListener {

            val item = movieList[position - 1]
            listener.onItemClick(item)

        }
        //holder.binding.backgroundView.setBackgroundColor(R.color.black)
        holder.binding.button.setOnClickListener {
            alertDialogShow(holder, position)
//            movieList.removeAt(position)
//            notifyItemRemoved(position)
//            notifyItemRangeChanged(position,movieList.size)
//            holder.itemView.visibility = View.GONE
//            val deletedItem = movieList[position].title
//            Toast.makeText(holder.itemView.context,"Deleted film is $deletedItem ",Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return movieList.size

    }

    private fun alertDialogShow(holder: MovieViewHolder, position: Int) {
        AlertDialog.Builder(holder.itemView.context)
            .setMessage("Do you really want to remove?")
            .setPositiveButton("Yes") { dialogInterface: DialogInterface, _: Int ->
                movieList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, movieList.size)
                holder.itemView.visibility = View.GONE
                val deletedItem = movieList[position - 1].title
                Toast.makeText(
                    holder.itemView.context,
                    "Deleted film is $deletedItem ",
                    Toast.LENGTH_LONG
                ).show()

            }.setNegativeButton("No") { dialogInterface: DialogInterface, _: Int ->
                Toast.makeText(
                    holder.itemView.context,
                    "You gave up from removing",
                    Toast.LENGTH_LONG
                ).show()

            }.setIcon(R.drawable.ic_baseline_warning_24)
            .setTitle("Warning")
            .create()
            .show()


    }

    interface OnItemClickListener {
        fun onItemClick(movie: Movie)
    }


}