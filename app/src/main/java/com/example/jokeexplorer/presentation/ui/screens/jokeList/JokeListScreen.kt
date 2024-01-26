package com.example.jokeexplorer.presentation.ui.screens.jokeList


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.jokeexplorer.data.local.entities.JokeEntity
import com.example.jokeexplorer.data.model.Joke
import com.example.jokeexplorer.presentation.viewmodels.JokeListViewModel
import com.example.jokeexplorer.util.*


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun JokeScreen() {
    val viewModel : JokeListViewModel = viewModel()
    val currentJoke by viewModel.currentJokeListFlow.collectAsState()
    val initialLoading by viewModel.loadingInitialJokeList.collectAsState()
    val initialListSize = viewModel.pageJokesCount
    val pagingJokes = viewModel.jokePagingFlow.collectAsLazyPagingItems()

    // Remember a CoroutineScope , listState
    val listState = rememberLazyListState()
    val autoLoaderBuffer = 3
    val autoLoadThreshold by remember {
        derivedStateOf {
            val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisible?.index != 0 && (lastVisible?.index
                ?: 0) >= listState.layoutInfo.totalItemsCount - autoLoaderBuffer
        }
    }
    val context = LocalContext.current
    LaunchedEffect(pagingJokes.loadState) {
        when (pagingJokes.loadState.refresh) {
            is LoadState.Error -> {
                Toast.makeText(
                    context,
                    "Error: ${(pagingJokes.loadState.refresh as LoadState.Error).error.message}",
                    Toast.LENGTH_LONG
                ).show()
            }

            is LoadState.Loading -> {
                Toast.makeText(
                    context,
                    "Loading: ${(pagingJokes.loadState.refresh as LoadState.Loading).endOfPaginationReached}",
                    Toast.LENGTH_LONG
                ).show()
            }

            is LoadState.NotLoading -> {
                Toast.makeText(
                    context,
                    "Not Loading: ${(pagingJokes.loadState.refresh as LoadState.NotLoading).endOfPaginationReached}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }
    Scaffold(modifier = Modifier.fillMaxSize())
    { paddingValues ->
        // Main content
        /*LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    bottom = paddingValues.calculateBottomPadding() + 8.dp,
                    top = 8.dp,
                    end = 8.dp,
                    start = 8.dp
                ),
            verticalArrangement = Arrangement.Center,
            state = listState,
            // ...
        ) {

            if (initialLoading) {
                for (id in range(0, initialListSize)) {
                    item {
                        SkeletonJokeCard()
                    }
                }

            } else {
                items(
                    currentJoke,
                    key = {
                        it.localId
                    }
                ) { joke ->

                    jokeItem(joke)

                }

            }

            // ... other content
        }
        */
        Box(modifier = Modifier.fillMaxSize()) {
            if(pagingJokes.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    state = listState
                ) {
                    items(pagingJokes.itemCount,key=pagingJokes.itemKey{it.id}) { index ->
                        val joke=pagingJokes.peek(index)
                        if(joke != null) {
                            jokeItem(
                                joke = joke,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                    item {
                        if(pagingJokes.loadState.append is LoadState.Loading) {

                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}


