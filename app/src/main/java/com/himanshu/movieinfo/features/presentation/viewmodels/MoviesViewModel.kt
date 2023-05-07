package com.himanshu.movieinfo.features.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himanshu.movieinfo.features.domain.entities.ResultEntity
import com.himanshu.movieinfo.features.domain.usecases.AddMovieToPlaylistUseCase
import com.himanshu.movieinfo.features.domain.usecases.AddPlaylistUseCase
import com.himanshu.movieinfo.features.domain.usecases.GetAllPlaylistsUseCase
import com.himanshu.movieinfo.features.domain.usecases.GetMoviesUseCase
import com.himanshu.movieinfo.features.domain.usecases.RemoveMovieFromPlaylistUseCase
import com.himanshu.movieinfo.features.presentation.mapper.MovieViewDataMapper
import com.himanshu.movieinfo.features.presentation.models.APIExceptionEvent
import com.himanshu.movieinfo.features.presentation.state.MovieViewModelState
import com.himanshu.movieinfo.features.presentation.utilities.callInViewModelScope
import com.himanshu.movieinfo.features.presentation.utilities.handleException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
	private val mapper: MovieViewDataMapper,
	private val getMoviesUseCase: GetMoviesUseCase,
	private val addPlaylistUseCase: AddPlaylistUseCase,
	private val getAllPlaylistsUseCase: GetAllPlaylistsUseCase,
	private val addMovieToPlaylistUseCase: AddMovieToPlaylistUseCase,
	private val removeMovieFromPlaylistUseCase: RemoveMovieFromPlaylistUseCase,
) : ViewModel() {
	private val viewModelState = MutableStateFlow(MovieViewModelState())
	val uiState = viewModelState.map { it.toUiState() }
		.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), viewModelState.value.toUiState())

	private val _apiExceptionEvent = MutableSharedFlow<APIExceptionEvent>()
	val apiExceptionEvent: SharedFlow<APIExceptionEvent> get() = _apiExceptionEvent

	init {
		getAllMovies()
	}

	private fun getAllMovies() = callInViewModelScope {
		updateLoader(true)

		when (val response = getMoviesUseCase()) {
			is ResultEntity.Success -> viewModelState.update {
				it.copy(
					movies = mapper.mapMoviesEntityToViewData(response.data),
					allPlayLists = getAllPlaylistsUseCase()
				)
			}

			is ResultEntity.Failure -> handleException(response, _apiExceptionEvent)
		}

		updateLoader(false)
	}

	private fun updateLoader(updateLoader: Boolean) = viewModelState.update {
		it.copy(isLoading = updateLoader)
	}

	fun setSelectedMovieId(id: Int) = viewModelState.update { it.copy(selectedMovieId = id) }

	fun addPlaylist(playlistName: String) = callInViewModelScope {
		viewModelState.update {
			it.copy(allPlayLists = addPlaylistUseCase(playlistName, it.allPlayLists))
		}
	}

	fun addMovieToPlaylist(playlistName: String) = callInViewModelScope {
		viewModelState.update {
			it.copy(
				movies = mapper.mapMoviesEntityToViewData(
					addMovieToPlaylistUseCase(
						it.selectedMovieId,
						playlistName,
						mapper.mapViewDataToMoviesEntity(it.movies)
					)
				)
			)
		}
	}

	fun removeMovieFromPlaylist(playlistName: String) = callInViewModelScope {
		viewModelState.update {
			it.copy(
				movies = mapper.mapMoviesEntityToViewData(
					removeMovieFromPlaylistUseCase(
						it.selectedMovieId,
						playlistName,
						mapper.mapViewDataToMoviesEntity(it.movies)
					)
				)
			)
		}
	}
}