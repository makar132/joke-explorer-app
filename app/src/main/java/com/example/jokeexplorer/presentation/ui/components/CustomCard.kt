package com.example.jokeexplorer.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jokeexplorer.R
import com.example.jokeexplorer.presentation.theme.pinkwarning

@Composable
fun CustomCard(
    imageUrl : String,
    subreddit : String,
    author : String,
    timestamp : String,
    flags : String,
    title : String,
    post : String?,
    subPost : String?,
    upvoteCount : Int,
    downvoteCount : Int,
    onPostClick : () -> Unit,
    onUpvoteClick : () -> Unit,
    onDownvoteClick : () -> Unit,
    onCommentClick : () -> Unit,
) {
    Surface(modifier = Modifier
        .padding(horizontal = 8.dp, vertical = 2.dp)
        .clickable { onPostClick() },
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.surfaceContainer
    ) {
        Column (modifier = Modifier.padding(8.dp)){
            // Add your ThumbnailImage function call here with imageUrl as parameter
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val iconPainter : Painter = when (subreddit.lowercase()) {
                    "any" -> painterResource(id = R.drawable.any)
                    "misc" -> painterResource(id = R.drawable.misc)
                    "programing" -> painterResource(id = R.drawable.programming)
                    "Dark" -> painterResource(id = R.drawable.dark)
                    "Pun" -> painterResource(id = R.drawable.pun)
                    "Spooky" -> painterResource(id = R.drawable.halloween)
                    "Christmas" -> painterResource(id = R.drawable.christmas)

                    else -> painterResource(id = R.drawable.any)
                }
                Image(
                    painter = iconPainter,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                //Icon(painter = iconPainter, contentDescription = "joke")
                Column(
                    modifier = Modifier.wrapContentWidth(), horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = subreddit,
                        modifier = Modifier.padding(end = 8.dp),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Row(
                        modifier = Modifier.wrapContentWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = author,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Cyan
                        )
                        Text(
                            text = ". $timestamp",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray

                        )
                    }

                }

            }
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                if (flags.isNotEmpty()) {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = null,
                        tint = pinkwarning,
                        modifier = Modifier
                            .padding(4.dp)
                            .size(15.dp)
                    )
                    Text(
                        text = flags,
                        style = MaterialTheme.typography.titleSmall,
                        color = pinkwarning,
                        modifier = Modifier.padding(4.dp)
                    )
                }

            }
            Row {
                Text(
                    text = title,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Row {
                when {
                    post != null -> {
                        Text(
                            text = post,
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                .wrapContentSize(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
            Row {
                when {
                    !(subPost.isNullOrEmpty()) -> {
                        Text(
                            text = subPost,
                            modifier = Modifier
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                                .wrapContentSize(),
                            style = MaterialTheme.typography.bodySmall,
                            color= MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Add your VoteButtons function call here with upvoteCount, downvoteCount,
                // onUpvoteClick, and onDownvoteClick as parameters
                // Add your CommentIcon function call here with onCommentClick as parameter
            }
        }
    }
}
