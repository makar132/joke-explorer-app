package com.example.jokeexplorer.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jokeexplorer.data.model.Joke
import com.example.jokeexplorer.presentation.ui.components.JokeCard
import com.example.jokeexplorer.presentation.viewmodels.JokeListViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun JokeScreen(viewModel : JokeListViewModel = viewModel()) {
    val currentJoke by viewModel.currentJokeListFlow.collectAsState()
    // Remember a CoroutineScope to be able to launch
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {

        // Bottom bar with refresh button
        BottomAppBar(
            // ...
        ) {
            Button(
                onClick = {
                    viewModel.fetchRandomJoke()
                },
                // ...
            ) {
                Text("Get Another Joke")
            }
        }
    }
    ) { paddingValues ->
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
            verticalArrangement = Arrangement.spacedBy(8.dp),
            state = listState,

            // ...
        ) {
            items(
                currentJoke,
                key = { "${it.id ?: 0}${it.joke ?: it.setup}" }
            ) { joke ->
                AnimatedVisibility(
                    visible = true,
                    enter = slideInVertically(
                        initialOffsetY = { -40 },
                        animationSpec = tween(durationMillis = 1300)
                    ),
                    exit = fadeOut(animationSpec = tween(durationMillis = 200))
                ){
                    jokeItem(joke)
                    coroutineScope.launch {
                        listState.animateScrollToItem(index = currentJoke.size - 1)
                    }
                }


            }

            // ... other content
        }


    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyItemScope.jokeItem(joke : Joke) {
    val color by animateColorAsState(
        targetValue = Color(0xff24d65f),
        animationSpec = tween(250),
        label = ""
    )

    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(
            initialOffsetY = { -40 },
            animationSpec = tween(durationMillis = 1300)
        ),
        exit = fadeOut(animationSpec = tween(durationMillis = 200))
    ) {


        JokeCard(
            joke = joke, Modifier.animateItemPlacement()
        )


    }
}
