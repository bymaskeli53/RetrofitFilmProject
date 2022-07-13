package com.example.retrofitfilmproject

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitfilmproject.databinding.FragmentMoviesBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

class MovieFragment : Fragment(R.layout.fragment_movies),MovieAdapter.OnItemClickListener {

    private lateinit var _binding : FragmentMoviesBinding
    private lateinit var navigationConttoller: NavController
    private var movieModels: List<Movie>? = null
    private val binding get() = _binding!!
    private var recyclerViewAdapter: MovieAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMoviesBinding.bind(view)
        Log.e("error","onview created çalıştı")
        println("onback pressed çalıştı")

        navigationConttoller = Navigation.findNavController(view)

        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerview.layoutManager = layoutManager


        val retrofit = Retrofit.Builder()
            .baseUrl(MovieApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

            val service = retrofit.create(MovieApi::class.java)
            val call = service.searchMovies()

            call.enqueue(object: Callback<ResponseObject>{
                override fun onResponse(call: Call<ResponseObject>, response: Response<ResponseObject>) {
                    if (response.isSuccessful) {
                        Toast.makeText(activity,"Successful",Toast.LENGTH_LONG).show()
                        response.body()?.let {
                            movieModels = it.results
                            recyclerViewAdapter = MovieAdapter(movieModels!! as MutableList<Movie>,this@MovieFragment)
                            binding.recyclerview.adapter = recyclerViewAdapter

                        }
                    }
                }

                override fun onFailure(call: Call<ResponseObject>, t: Throwable) {

                    Toast.makeText(activity,"error $t",Toast.LENGTH_LONG).show()
                }

            })





//        binding.recyclerview.setOnClickListener {
//            val bundle = bundleOf()
//             navigationConttoller.navigate(
//                 R.id.action_movieFragment_to_detailsFragment
//             )
//        }



    }

    override fun onItemClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToDetailsFragment(movie)
        findNavController().navigate(action)

    }


}