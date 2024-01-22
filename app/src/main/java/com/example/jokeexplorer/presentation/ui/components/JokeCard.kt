package com.example.jokeexplorer.presentation.ui.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.ContentAlpha
import com.example.jokeexplorer.data.model.Joke

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun JokeCard(joke : Joke, modifier : Modifier) {
    var isVisible by remember { mutableStateOf(true) }

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier
            .padding(16.dp)
            .animateContentSize()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = joke.category,
                style = typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
                , verticalAlignment=Alignment.CenterVertically
                , horizontalArrangement = Arrangement.Start
            ) {
                AnimatedVisibility(
                    visible = joke.getJokeFlags(joke.flags).isNotEmpty(),
                ) {
                Icon(
                    Icons.Default.Warning,
                    tint = Color.Red,
                    contentDescription = null,
                )
                }

                Text(
                    modifier =Modifier.padding(4.dp),
                    text = joke.getJokeFlags(joke.flags),
                    style = MaterialTheme.typography.bodySmall
                )
            }

            if (joke.setup != null && joke.delivery != null) {
                // Two-part joke
                Text(
                    text = joke.setup,
                    style = typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = joke.delivery,
                    style = typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5C6BC0) // Vibrant accent color
                )
            } else {
                // One-line joke
                joke.joke?.let {
                    Text(
                        text = it,
                        style = typography.titleSmall,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }



            // Optionally display additional information (category, flags)
        }
    }

}


