package com.himanshu.movieinfo.features.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.himanshu.movieinfo.features.presentation.theme.MovieInfoTheme
import com.himanshu.movieinfo.features.presentation.ui.MoviesScreen
import com.himanshu.movieinfo.features.presentation.ui.components.HandleAPIException
import com.himanshu.movieinfo.features.presentation.ui.components.ShowProgress
import com.himanshu.movieinfo.features.presentation.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MovieInfoTheme {
				val viewModel: MoviesViewModel = hiltViewModel()
				val uiState by viewModel.uiState.collectAsStateWithLifecycle()

				Surface(
					modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
				) {
					HandleAPIException(viewModel.apiExceptionEvent)

					ShowProgress(uiState.isLoading)

					MoviesScreen(
						uiState.movies,
						uiState.allPlayLists,
						viewModel::setSelectedMovieId,
						viewModel::addPlaylist,
						viewModel::addMovieToPlaylist,
						viewModel::removeMovieFromPlaylist
					)
				}
			}
		}
	}
}