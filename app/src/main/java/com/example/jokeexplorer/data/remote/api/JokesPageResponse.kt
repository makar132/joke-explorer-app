package com.example.jokeexplorer.data.remote.api

import com.example.jokeexplorer.data.remote.JokeDto

data class JokesPageResponse(
    val error : Boolean,
    val amount : Int,
    val jokes:List<JokeDto>
)
