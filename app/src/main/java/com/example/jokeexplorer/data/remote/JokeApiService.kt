package com.example.jokeexplorer.data.remote

import com.example.jokeexplorer.data.model.Joke
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface JokeApiService {
    @Headers("X-RapidAPI-Key: cc55ab2806mshc467ef35a1ee030p1c7c6bjsn7bc11143303a",
        "X-RapidAPI-Host: jokeapi-v2.p.rapidapi.com")

    @GET("joke/Any?format=json&blacklistFlags=nsfw%2Cracist")
    fun getRandomJoke(): Call<Joke>

    //get available jokes categories
    @GET("categories?format=json")
    fun getJokesCategories(): Call<List<String>>

    //get certain jokes category
    @GET("joke/{category}?format=json&blacklistFlags=nsfw%2Cracist")
    fun getJokesByCategory(@Path("category") category: String): Call<List<Joke>>
}