package com.example.jokeexplorer.presentation.ui.screens.jokeList


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.example.jokeexplorer.domain.Joke
import com.example.jokeexplorer.presentation.viewmodels.JokeListViewModel
import com.example.jokeexplorer.util.*


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun JokeScreen(jokes:LazyPagingItems<Joke>) {

    val listState = rememberLazyListState()
    val context = LocalContext.current
    LaunchedEffect(key1=jokes.loadState) {
        if (jokes.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: ${(jokes.loadState.refresh as LoadState.Error).error.message}",
                Toast.LENGTH_LONG
            ).show()
        }

    }
    Scaffold(modifier = Modifier.fillMaxSize())
    {
        Box(modifier = Modifier.fillMaxSize()) {
            if(jokes.loadState.refresh is LoadState.Loading) {
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
                    items(jokes.itemCount) { index ->
                        val joke=jokes[index]
                        if(joke != null) {
                            jokeItem(
                                joke = joke,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                    item {
                        if(jokes.loadState.append is LoadState.Loading) {

                            CircularProgressIndicator()
                        }
                        else {
                            Text(
                                text = "---NO MORE DATA--",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}


