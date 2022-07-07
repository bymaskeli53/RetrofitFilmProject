package com.example.retrofitfilmproject

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitfilmproject.databinding.FragmentMoviesBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

class MovieFragment : Fragment(R.layout.fragment_movies) {

    private lateinit var _binding : FragmentMoviesBinding
    private var cryptoModels: List<Movie>? = null
    private val binding get() = _binding!!
    private var recyclerViewAdapter: MovieAdapter? = null
    private lateinit var response: Response<List<Movie>>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMoviesBinding.bind(view)

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
                            cryptoModels = it.results
                            recyclerViewAdapter = MovieAdapter(cryptoModels!!)
                            binding.recyclerview.adapter = recyclerViewAdapter

                        }
                    }
                }

                override fun onFailure(call: Call<ResponseObject>, t: Throwable) {
                    Log.e("awdawd",t.toString())
                    Toast.makeText(activity,"error $t",Toast.LENGTH_LONG).show()
                }

            })


          // response =  service.searchMovies()






    }



    override fun onDestroyView() {
        super.onDestroyView()
       // _binding = null
    }
}