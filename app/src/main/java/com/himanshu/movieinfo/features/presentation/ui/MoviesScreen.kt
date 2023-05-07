package com.himanshu.movieinfo.features.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.himanshu.movieinfo.R
import com.himanshu.movieinfo.features.presentation.state.MovieViewData
import com.himanshu.movieinfo.features.presentation.ui.components.FilterMovieByPlaylistView
import com.himanshu.movieinfo.features.presentation.ui.components.MovieCard
import com.himanshu.movieinfo.features.presentation.ui.components.PlayListScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoviesScreen(
	movies: List<MovieViewData>,
	allPlayLists: List<String>,
	setSelectedMovieId: (Int) -> Unit,
	addPlaylist: (String) -> Unit,
	addMovieToPlaylist: (String) -> Unit,
	removeMovieFromPlaylist: (String) -> Unit,
) {
	var showFilterByPlaylistView by remember { mutableStateOf(false) }
	val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
	val scope: CoroutineScope = rememberCoroutineScope()
	val openBottomSheet: () -> Unit = {
		scope.launch {
			if (sheetState.isVisible.not()) {
				sheetState.show()
			}
		}
	}
	val filterPlaylistName = remember { mutableStateOf("") }
	val filteredMovies by remember(filterPlaylistName, movies) {
		derivedStateOf {
			if (filterPlaylistName.value.isBlank()) {
				movies
			} else {
				movies.filter { movie ->
					filterPlaylistName.value in movie.playlists
				}
			}
		}
	}

	ModalBottomSheetLayout(sheetState = sheetState,
		sheetShape = MaterialTheme.shapes.medium,
		sheetContent = {
			if (showFilterByPlaylistView) {
				FilterMovieByPlaylistView(allPlayLists, filterPlaylistName)
			} else {
				PlayListScreen(
					allPlayLists, addMovieToPlaylist, addPlaylist, removeMovieFromPlaylist
				)
			}
		}) {
		Scaffold(floatingActionButton = {
			FloatingActionButton(onClick = {
				showFilterByPlaylistView = true
				openBottomSheet()
			}) {
				Icon(
					tint = MaterialTheme.colorScheme.primary,
					painter = painterResource(id = R.drawable.ic_filter),
					contentDescription = stringResource(id = R.string.click_to_filter_by_playlist)
				)
			}
		}) {
			LazyColumn(
				Modifier
					.fillMaxSize()
					.padding(it), contentPadding = PaddingValues(10.dp)
			) {
				items(filteredMovies) {
					MovieCard(movie = it) {
						showFilterByPlaylistView = false
						setSelectedMovieId(it)
						openBottomSheet()
					}
				}
			}
		}
	}
}

