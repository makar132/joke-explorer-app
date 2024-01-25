package com.example.jokeexplorer.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.jokeexplorer.data.local.dao.JokeDao
import com.example.jokeexplorer.data.local.database.AppDatabase
import com.example.jokeexplorer.data.local.entities.JokeEntity
import com.example.jokeexplorer.data.remote.JokeRemoteMediator
import com.example.jokeexplorer.data.remote.NetworkModule
import com.example.jokeexplorer.data.remote.api.JokeApi
import com.example.jokeexplorer.data.repository.JokeRepository
import com.example.jokeexplorer.presentation.viewmodels.JokeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@OptIn(ExperimentalPagingApi::class)
val appModules = module {
    single { AppDatabase.getDatabase(get()) }//database
    single { get<AppDatabase>().jokeDao() }//dao
    single {NetworkModule().provideRetrofit()}//retrofit class
    single { NetworkModule().provideJokeApi(get())}//api
    single { JokeRepository(get()) }//repository
    single { JokeRemoteMediator(get<AppDatabase>(),get<JokeApi>(),get<JokeDao>())}//remote mediator
    single {
        Pager<Int,JokeEntity>(
            config = PagingConfig(pageSize = 10),
            remoteMediator = get<JokeRemoteMediator>(),
            pagingSourceFactory = {get<JokeDao>().pagingSource()}
        )
    }
    viewModel { JokeListViewModel() }
}