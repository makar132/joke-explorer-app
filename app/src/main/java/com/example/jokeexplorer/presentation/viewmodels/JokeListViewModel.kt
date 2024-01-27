package com.example.jokeexplorer.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.jokeexplorer.data.local.entities.JokeEntity
import com.example.jokeexplorer.data.mappers.toJoke
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class JokeListViewModel(

) : ViewModel(), KoinComponent {

    private val pager :Pager<Int,JokeEntity> by inject()
    val jokePagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map {
                it.toJoke()
            }
        }.cachedIn(viewModelScope)


}