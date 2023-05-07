package com.himanshu.movieinfo.features.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.himanshu.movieinfo.R

@Composable
fun FilterMovieByPlaylistView(
	allPlayLists: List<String>, filterPlaylistName: MutableState<String>
) {

	if (allPlayLists.any()) {
		Text(stringResource(id = R.string.clear_filter),
			textDecoration = TextDecoration.Underline,
			modifier = Modifier
				.padding(start = 20.dp, top = 20.dp)
				.clickable { filterPlaylistName.value = "" })
		LazyColumn(contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)) {
			items(allPlayLists) {
				Text(text = it,
					color = Color.Gray,
					modifier = Modifier
						.padding(bottom = 20.dp)
						.fillMaxWidth()
						.clickable { filterPlaylistName.value = it })
			}
		}
	} else {
		Box(
			Modifier
				.fillMaxWidth()
				.height(300.dp)
		) {
			Text(
				stringResource(id = R.string.no_playlist_exists), Modifier.align(Alignment.Center)
			)
		}
	}
}