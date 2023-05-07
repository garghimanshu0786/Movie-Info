package com.himanshu.movieinfo.features.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.himanshu.movieinfo.R

@Composable
fun PlayListScreen(
	allPlayLists: List<String>,
	addMovieToPlaylist: (String) -> Unit,
	addPlaylist: (String) -> Unit,
	removeMovieFromPlaylist: (String) -> Unit,
) {
	var showPopup by remember { mutableStateOf(false) }
	Column(Modifier.padding(20.dp)) {
		allPlayLists.forEach {
			Row(
				modifier = Modifier.wrapContentHeight(),
				verticalAlignment = Alignment.CenterVertically,
			) {
				Text(text = it,
					color = Color.Gray,
					modifier = Modifier
						.weight(.6f)
						.height(30.dp)
						.clickable { addMovieToPlaylist(it) }
				)
				Text(text = stringResource(id = R.string.remove),
					textDecoration = TextDecoration.Underline,
					textAlign = TextAlign.Center,
					modifier = Modifier
						.weight(.4f)
						.height(30.dp)
						.clickable { removeMovieFromPlaylist(it) }
				)
			}
		}

		Row(verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.padding(top = 10.dp)
				.clickable { showPopup = true }) {
			Icon(
				painter = painterResource(id = R.drawable.ic_plus),
				tint = Color.Gray,
				modifier = Modifier.padding(end = 10.dp),
				contentDescription = stringResource(id = R.string.click_to_add_to_playlist)
			)
			Text(stringResource(id = R.string.add_a_playlist), color = Color.Gray)
		}

		AddPlaylistPopup(showPopup = showPopup, addPlaylist = addPlaylist) {
			showPopup = false
		}
	}
}
