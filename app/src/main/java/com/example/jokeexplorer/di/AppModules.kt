package com.example.jokeexplorer.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.RemoteMediator
import androidx.room.Room
import com.example.jokeexplorer.data.local.dao.JokeDao
import com.example.jokeexplorer.data.local.database.AppDatabase
import com.example.jokeexplorer.data.local.entities.JokeEntity
import com.example.jokeexplorer.data.remote.JokeRemoteMediator
import com.example.jokeexplorer.data.remote.api.JokeApi
import com.example.jokeexplorer.presentation.viewmodels.JokeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@OptIn(ExperimentalPagingApi::class)
val appModules = module {
    single {   Room.databaseBuilder(
        get(),
        AppDatabase::class.java,
        "jokes.db"
    ).build() } //database

    single {
        get<AppDatabase>().dao
    }

    single { Retrofit.Builder()
        .baseUrl(JokeApi.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(JokeApi::class.java)
    }//api
    single { JokeRemoteMediator(get<AppDatabase>(),get<JokeApi>(),get<JokeDao>())}//remote mediator
    single {
        Pager<Int,JokeEntity>(
            config = PagingConfig(pageSize = 10),
            remoteMediator = get<JokeRemoteMediator>(),
            pagingSourceFactory = {get<JokeDao>().pagingSource()},

        )
    }//pager
    //viewModel { JokeListViewModel() }
}