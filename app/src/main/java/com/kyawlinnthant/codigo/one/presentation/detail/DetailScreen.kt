package com.kyawlinnthant.codigo.one.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kyawlinnthant.codigo.one.domain.model.detail.MovieDetail

@Composable
fun DetailScreen(
    movie: MovieDetail,
) {
    Scaffold {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it), contentAlignment = Alignment.Center) {
            Text(text = "$movie")
        }
    }
}