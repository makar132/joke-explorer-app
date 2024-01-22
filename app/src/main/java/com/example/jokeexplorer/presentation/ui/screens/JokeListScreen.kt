package com.example.jokeexplorer.presentation.ui.screens

import androidx.compose.runtime.LaunchedEffect


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jokeexplorer.presentation.ui.components.SkeletonJokeCard
import com.example.jokeexplorer.presentation.viewmodels.JokeListViewModel
import com.example.jokeexplorer.util.*
import kotlinx.coroutines.launch
import java.util.stream.IntStream.range
import kotlin.math.abs


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun JokeScreen(viewModel : JokeListViewModel = viewModel()) {
    val currentJoke by viewModel.currentJokeListFlow.collectAsState()
    val initialLoading by viewModel.loadingInitialJokeList.collectAsState()
    val initialListSize = viewModel.pageJokesCount
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
    LaunchedEffect(autoLoadThreshold) {
        if (autoLoadThreshold) viewModel.loadNextPageOfJokes()
    }
    Scaffold(modifier = Modifier.fillMaxSize())
    { paddingValues ->
        // Main content
        LazyColumn(
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


    }
}


