package com.example.jokeexplorer.presentation.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.jokeexplorer.data.model.Joke
import com.example.jokeexplorer.data.repository.JokeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class JokeListViewModel() :ViewModel(),KoinComponent{
    private val repository:JokeRepository by inject()
    private var _currentJokeList = mutableStateListOf<Joke>()
    private var _currentJokeListFlow = MutableStateFlow(_currentJokeList.toList())
    val currentJokeListFlow = _currentJokeListFlow.asStateFlow()

    init {
        fetchRandomJoke()
    }
    // Make the API call
    fun fetchRandomJoke() {
        viewModelScope.launch {
            try {
                val joke = repository.getRandomJoke()
                if (joke != null) {
                    _currentJokeList.add(joke)
                    _currentJokeListFlow.value=_currentJokeList
                }
            } catch (e: Exception) {
                // Handle errors gracefully
            }
        }
    }
}