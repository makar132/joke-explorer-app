package com.example.jokeexplorer.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.jokeexplorer.data.model.JokeFlags

@Entity
@TypeConverters(JokeFlagsTypeConverter::class)
data class JokeEntity(
    @PrimaryKey
    val id: Int=-1,
    val category: String="",
    val error:Boolean=false,
    val type: String="",
    val joke: String?=null,
    val setup: String?=null,
    val delivery: String?=null,
    val flags: JokeFlags=JokeFlags(),
    val language: String="",
    var localId:Int=0
)
class JokeFlagsTypeConverter{
    @TypeConverter
    fun fromJokeFlags(jokeFlags : JokeFlags):String {
        val flags= mutableListOf<String>()
        if(jokeFlags.nsfw)flags.add("nsfw")
        if(jokeFlags.religious)flags.add("religious")
        if(jokeFlags.political)flags.add("political")
        if(jokeFlags.racist)flags.add("racist")
        if(jokeFlags.sexist)flags.add("sexist")
        if(jokeFlags.explicit)flags.add("explicit")

        return flags.joinToString(",")
    }

    @TypeConverter
    fun toJokeFlags(value : String) : JokeFlags {

        return JokeFlags(
            value.contains("nsfw"),
            value.contains("religious"),
            value.contains("political"),
            value.contains("racist"),
            value.contains("sexist"),
            value.contains("explicit")
        )
    }

}