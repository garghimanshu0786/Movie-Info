package com.himanshu.movieinfo.features.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.himanshu.movieinfo.R
import com.himanshu.movieinfo.features.presentation.state.MovieViewData
import com.himanshu.movieinfo.features.presentation.theme.Gray

@Composable
fun MovieCard(movie: MovieViewData, addToFavorite: (Int) -> Unit) {
	Column(
		Modifier
			.padding(10.dp)
			.background(Gray)
	) {
		MovieImage(movie.image)

		Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
			Column(
				Modifier
					.weight(.8f)
					.padding(15.dp)
			) {

				Text(movie.name, fontSize = 20.sp)

				Text(
					"${stringResource(id = R.string.rating)} - ${movie.rating}", color = Color.Gray
				)

				Text(movie.playlists.joinToString(", "), color = Color.Gray)
			}

			Image(painter = painterResource(id = R.drawable.ic_star),
				contentDescription = stringResource(id = R.string.click_to_see_playlists),
				Modifier
					.weight(.2f)
					.clickable { addToFavorite(movie.id) })
		}
	}
}