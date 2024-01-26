package com.example.jokeexplorer.data.remote.api

import com.example.jokeexplorer.data.model.Joke
import com.example.jokeexplorer.data.remote.JokeDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface JokeApi {
    @Headers("X-RapidAPI-Key: cc55ab2806mshc467ef35a1ee030p1c7c6bjsn7bc11143303a",
        "X-RapidAPI-Host: jokeapi-v2.p.rapidapi.com")
    @GET("joke/Any?format=json")
    suspend fun getRandomJoke(): Call<Joke>
    @Headers("X-RapidAPI-Key: 4f0fe32599msh71ca95fa54916ecp15eacejsnd9de37564233",
        "X-RapidAPI-Host: jokeapi-v2.p.rapidapi.com")
    @GET("joke/Any?format=json")
    suspend fun getJokesPage(
        @Query("amount") amount: Int = 10,
        @Query("idRange") idRange : String
        ): JokesPageResponse

    //get available jokes categories
    @GET("categories?format=json")
    fun getJokesCategories(): Call<List<String>>

    //get certain jokes category
    @GET("joke/{category}?format=json")
    fun getJokesByCategory(@Path("category") category: String): Call<List<Joke>>


}