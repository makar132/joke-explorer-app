package com.example.jokeexplorer.presentation.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeexplorer.data.model.Joke
import com.example.jokeexplorer.data.repository.JokeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.stream.IntStream.range


class JokeListViewModel() :ViewModel(),KoinComponent{
    private val repository:JokeRepository by inject()
    private var _currentJokeList = mutableStateListOf<Joke>()
    private var _currentJokeListFlow = MutableStateFlow(_currentJokeList.toList())
    val currentJokeListFlow = _currentJokeListFlow.asStateFlow()
    val pageJokesCount=7
    private val _loadingInitialJokeList= MutableStateFlow(true)
    val loadingInitialJokeList: StateFlow<Boolean>
        get() = _loadingInitialJokeList

    init {
        viewModelScope.launch {
        _loadingInitialJokeList.value=true
        fetchRandomJokes()
        delay(5500)
        _loadingInitialJokeList.value=false
        }
    }
    // Make the API call
    private fun fetchRandomJokes(fetchJokesCount:Int = pageJokesCount) {
        viewModelScope.launch {

            for(call in range(0,pageJokesCount)){
                try {
                    val joke = repository.getRandomJoke()
                    if (joke != null) {
                        joke.localId=_currentJokeList.size
                        _currentJokeList.add(joke)
                        _currentJokeListFlow.value=_currentJokeList
                    }
                } catch (e: Exception) {
                    // Handle errors gracefully
                }
            }

        }
    }
    fun loadNextPageOfJokes(){
        fetchRandomJokes()
    }
}