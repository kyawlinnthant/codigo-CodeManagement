package com.kyawlinnthant.codigo.one.presentation.popular


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.kyawlinnthant.codigo.one.R
import com.kyawlinnthant.codigo.one.domain.model.movie.Movie

@Composable
fun PopularScreen(
    movies: LazyPagingItems<Movie>,
    onAction: (PopularAction) -> Unit
) {
    movies.apply {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = rememberLazyListState(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                count = movies.itemCount,
                key = movies.itemKey { it.id }
            ) { index ->

                val currentMovie = movies[index] ?: Movie()
                ListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onAction(PopularAction.Navigate(id = currentMovie.id))
                        },
                    headlineContent = {
                        Text(text = "$index $currentMovie")
                    },
                    trailingContent = {
                        FilledIconToggleButton(
                            checked = currentMovie.isFavourite,
                            onCheckedChange = {
                                onAction(
                                    PopularAction.Favourite(
                                        id = currentMovie.id,
                                        enabled = it
                                    )
                                )
                            }) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null,
                                tint = if (currentMovie.isFavourite) Color.Red else Color.Gray
                            )
                        }
                    },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                )
            }
            item {
                if (loadState.append is LoadState.Loading || loadState.mediator?.refresh is LoadState.Loading) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }
            item {
                if (loadState.append is LoadState.Error) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                        TextButton(onClick = { retry() }) {
                            Text(text = stringResource(id = R.string.retry))
                        }
                    }
                }
            }
            item {
                if (loadState.mediator?.refresh is LoadState.Error) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        TextButton(onClick = { retry() }) {
                            Text(text = stringResource(id = R.string.retry))
                        }
                    }
                }
            }
            item {
                if (loadState.append.endOfPaginationReached) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                    }
                    ListItem(headlineContent = { Text(text = "...") })
                }
            }
        }

    }
}