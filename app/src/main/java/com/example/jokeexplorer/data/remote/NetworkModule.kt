package com.example.jokeexplorer.data.remote

import com.example.jokeexplorer.data.remote.api.JokeApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule {
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jokeapi-v2.p.rapidapi.com") // Base URL for JokeAPI v2
            .addConverterFactory(GsonConverterFactory.create()) // For JSON parsing
            .build()
    }

    fun provideJokeApi(retrofit: Retrofit): JokeApi {
        return retrofit.create(JokeApi::class.java)
    }
}