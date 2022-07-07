package com.example.retrofitfilmproject

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    companion object {
        const val BASE_URL = "https://swapi.dev"
    }

    @GET("/api/films/")
     fun searchMovies(): Call<ResponseObject>

}