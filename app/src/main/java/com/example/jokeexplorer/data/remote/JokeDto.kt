package com.example.jokeexplorer.data.remote

import com.example.jokeexplorer.data.model.JokeFlags

data class JokeDto(
    val error:Boolean=false,
    val id: Int=-1,
    val category: String="",
    val type: String="",
    val joke: String?=null,
    val setup: String?=null,
    val delivery: String?=null,
    val flags: JokeFlags = JokeFlags(),
    val language: String="",
    var localId:Int=0
)
