package com.example.jokeexplorer.data.model
data class Joke(
    val error:Boolean=false,
    val id: Int=-1,
    val category: String="",
    val type: String="",
    val joke: String?=null,
    val setup: String?=null,
    val delivery: String?=null,
    val flags: JokeFlags=JokeFlags(),
    val language: String="",
    var localId:Int=0
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
    val nsfw: Boolean=false,
    val religious: Boolean=false,
    val political: Boolean=false,
    val racist: Boolean=false,
    val sexist: Boolean=false,
    val explicit: Boolean=false
)
