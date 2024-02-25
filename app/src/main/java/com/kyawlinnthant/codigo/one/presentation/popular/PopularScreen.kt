package com.kyawlinnthant.codigo.one.presentation.popular


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
        when {
            loadState.refresh is LoadState.Loading && this.itemCount == 0 -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            loadState.refresh is LoadState.Error && this.itemCount == 0 -> {
                val error = (loadState.refresh as LoadState.Error).error.message ?: stringResource(
                    id = R.string.wrong
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = error)
                    OutlinedButton(onClick = {
                        retry()
                    }) {
                        Text(text = stringResource(id = R.string.retry))
                    }
                }

            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = rememberLazyListState()
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
                        Text(text = "$currentMovie")
                    }
                )
            }
            item {
                if (loadState.append is LoadState.Loading || loadState.mediator?.refresh is LoadState.Loading) {
                    CircularProgressIndicator()
                }
            }
            item {
                if (loadState.append is LoadState.Error) {
                    TextButton(onClick = { retry() }) {
                        Text(text = stringResource(id = R.string.retry))
                    }
                }
            }
            item {
                if (loadState.mediator?.refresh is LoadState.Error) {
                    TextButton(onClick = { retry() }) {
                        Text(text = stringResource(id = R.string.retry))
                    }
                }
            }
            item {
                if (loadState.append.endOfPaginationReached) {
                    ListItem(headlineContent = { Text(text = "...") })
                }
            }
        }

    }
}