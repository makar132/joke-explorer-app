package com.example.jokeexplorer.data.model
data class Joke(
    val error:Boolean,
    val id: Int,
    val category: String,
    val type: String,
    val joke: String?,
    val setup: String?,
    val delivery: String?,
    val flags: JokeFlags,
    val language: String
) {
    fun getJokeFlags(jokeFlags : JokeFlags):String{
        val flags= mutableListOf<String>()
        if(jokeFlags.nsfw)flags.add("nsfw")
        if(jokeFlags.religious)flags.add("nsfw")
        if(jokeFlags.political)flags.add("nsfw")
        if(jokeFlags.racist)flags.add("nsfw")
        if(jokeFlags.sexist)flags.add("nsfw")
        if(jokeFlags.explicit)flags.add("nsfw")

        return flags.joinToString(",")
    }
}

data class JokeFlags(
    val nsfw: Boolean,
    val religious: Boolean,
    val political: Boolean,
    val racist: Boolean,
    val sexist: Boolean,
    val explicit: Boolean
)
