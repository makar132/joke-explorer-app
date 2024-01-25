package com.example.jokeexplorer.data.remote

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.jokeexplorer.data.local.dao.JokeDao
import com.example.jokeexplorer.data.local.database.AppDatabase
import com.example.jokeexplorer.data.local.entities.JokeEntity
import com.example.jokeexplorer.data.mappers.toJokeEntity
import com.example.jokeexplorer.data.remote.api.JokeApi
import retrofit2.awaitResponse
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class JokeRemoteMediator(
    private val database : AppDatabase,
    private val api:JokeApi,
    private val dao : JokeDao
) : RemoteMediator<Int, JokeEntity>() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType : LoadType,
        state : PagingState<Int, JokeEntity>
    ) : MediatorResult {
        return try {
            val loadKey =when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem=state.lastItemOrNull()
                    if(lastItem == null){
                        1
                    }
                    else {
                        (lastItem.id/state.config.pageSize) + 1
                    }
                }
            }

            val jokes=api.getJokesPage(
                idRange = "${(loadKey-1)*state.config.pageSize}-${(loadKey*state.config.pageSize)-1}"
            ).jokes
            database.withTransaction {
                dao.clearAll()
            }
            val jokeEntities= jokes.map { it.toJokeEntity() }
            dao.upsertAll(jokeEntities)
            MediatorResult.Success(
                endOfPaginationReached = jokes.isEmpty()
            )
        }
        catch (e:IOException){
            MediatorResult.Error(e)
        }
        catch (e:HttpException)
        {
            MediatorResult.Error(e)
        }
    }

}