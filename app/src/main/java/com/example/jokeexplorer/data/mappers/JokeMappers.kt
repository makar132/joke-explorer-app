package com.example.jokeexplorer.data.mappers

import com.example.jokeexplorer.data.local.entities.JokeEntity
import com.example.jokeexplorer.domain.Joke
import com.example.jokeexplorer.data.remote.JokeDto

fun JokeDto.toJokeEntity():JokeEntity {
    return JokeEntity(
        error=error,
        id=id,
        category=category,
        type=type,
        joke=joke,
        setup=setup,
        delivery=delivery,
        flags=flags,
        language=language
    )
}
fun JokeEntity.toJoke(): Joke {
    return Joke(
        error=error,
        id=id,
        category=category,
        type=type,
        joke=joke,
        setup=setup,
        delivery=delivery,
        flags=flags,
        language=language
    )
}