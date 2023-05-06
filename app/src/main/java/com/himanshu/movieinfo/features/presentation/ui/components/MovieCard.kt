package com.himanshu.movieinfo.features.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.himanshu.movieinfo.R
import com.himanshu.movieinfo.features.presentation.state.MovieViewData

@Composable
fun MovieCard(movie: MovieViewData) {

	MovieImage(movie.image)

	Column(Modifier.padding(15.dp)) {

		Text(movie.name, fontSize = 20.sp)

		Text("${stringResource(id = R.string.rating)} -${movie.rating}", color = Color.Gray)

		Row {
			movie.playlists.forEach {
				Text("${stringResource(id = R.string.playlist)} $it", color = Color.Gray)
			}
		}
	}
}