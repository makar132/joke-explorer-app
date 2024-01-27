package com.example.jokeexplorer.util

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp
import com.example.jokeexplorer.domain.Joke
import com.example.jokeexplorer.presentation.ui.components.CustomCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.jokeItem(joke : Joke, modifier : Modifier = Modifier) {


    /*JokeCard(
        joke = joke, Modifier.animateItemPlacement()
    )*/
    CustomCard(
        imageUrl = "",
        subreddit = joke.type,
        author = "jokerApp",
        timestamp = joke.id.toString(),
        flags = joke.getJokeFlags(joke.flags),
        title = joke.category,
        post = joke.joke ?: joke.setup,
        subPost = joke.delivery,
        upvoteCount = 10,
        downvoteCount = 10,
        onPostClick = { /*TODO*/ },
        onUpvoteClick = { /*TODO*/ },
        onDownvoteClick = { /*TODO*/ }) {

    }
    HorizontalDivider(modifier = Modifier.padding(top = 4.dp, bottom = 4.dp))


}

fun Modifier.shimmerBackground(shape : Shape = RectangleShape) : Modifier = composed {
    val transition = rememberInfiniteTransition(label = "")

    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 400f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1500, easing = LinearOutSlowInEasing),
            RepeatMode.Restart
        ),
        label = "",
    )
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.9f),
        Color.LightGray.copy(alpha = 0.4f),
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnimation, translateAnimation),
        end = Offset(translateAnimation + 100f, translateAnimation + 100f),
        tileMode = TileMode.Mirror,
    )
    return@composed this.then(background(brush, shape))
}