package com.example.jokeexplorer.presentation.ui.screens.jokeList

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jokeexplorer.data.model.Joke
import com.example.jokeexplorer.data.repository.JokeRepository

class JokeListPaging (private val repository : JokeRepository): PagingSource<Int, Joke>() {
    override suspend fun load(params : LoadParams<Int>) : LoadResult<Int, Joke> {
        //TODO("Not yet implemented")
        val nextPageNumber=params.key ?:1
        val data=repository.getRandomJoke()
        return if (data == null) {
            LoadResult.Error(Throwable("NoResponse"))

        } else {
            try {
                LoadResult.Page(
                    data = listOf(data),
                    prevKey = null,
                    nextKey = nextPageNumber + 1

                )
            } catch (exception:Exception){
                LoadResult.Error(exception)
            }
        }

    }

    override fun getRefreshKey(state : PagingState<Int, Joke>) : Int? {
        //TODO("Not yet implemented")
        // This is called when the list is invalidated and needs to be reloaded
        // Return the key associated with the first item in the list
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


}