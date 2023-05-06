package com.himanshu.movieinfo.features.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.himanshu.movieinfo.features.presentation.state.MovieUIState
import com.himanshu.movieinfo.features.presentation.theme.Gray
import com.himanshu.movieinfo.features.presentation.ui.components.MovieCard

@Composable
fun MoviesScreen(
	scaffoldState: ScaffoldState,
	uiState: MovieUIState,
) {
	Scaffold(scaffoldState = scaffoldState) {
		LazyColumn(
			modifier = Modifier
				.padding(it)
				.fillMaxSize(),
			contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
		) {
			items(uiState.movies) {
				Column(
					Modifier
						.padding(10.dp)
						.background(Gray)
				) {
					MovieCard(movie = it)
				}
			}
		}
	}
}

