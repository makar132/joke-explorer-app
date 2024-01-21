package com.example.jokeexplorer.di

import com.example.jokeexplorer.data.remote.JokeApiService
import com.example.jokeexplorer.data.remote.NetworkModule
import com.example.jokeexplorer.data.repository.JokeRepository
import com.example.jokeexplorer.presentation.viewmodels.JokeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val appModules = module {
    single {NetworkModule().provideRetrofit()}
    single { NetworkModule().provideJokeService(get())}
    /*single {
        Retrofit.Builder()
            .baseUrl("https://jokeapi-v2.p.rapidapi.com") // Base URL for JokeAPI v2
            .addConverterFactory(GsonConverterFactory.create()) // For JSON parsing
            .build()
            .create(JokeApiService::class.java)
    }*/
    single {
        JokeRepository(get())
    }
    viewModel { JokeListViewModel() }
}