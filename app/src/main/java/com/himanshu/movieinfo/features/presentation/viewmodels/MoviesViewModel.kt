package com.himanshu.movieinfo.features.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himanshu.movieinfo.base.utilities.callInViewModelScope
import com.himanshu.movieinfo.base.utilities.handleException
import com.himanshu.movieinfo.features.domain.entity.ResultEntity
import com.himanshu.movieinfo.features.domain.usecases.GetMoviesUseCase
import com.himanshu.movieinfo.features.presentation.mapper.MovieViewDataMapper
import com.himanshu.movieinfo.features.presentation.models.APIExceptionEvent
import com.himanshu.movieinfo.features.presentation.state.MovieViewModelState
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
	private val getMoviesUseCase: GetMoviesUseCase,
	private val mapper: MovieViewDataMapper,
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
				it.copy(movies = mapper.mapMoviesEntityToViewData(response.data))
			}

			is ResultEntity.Failure -> handleException(response, _apiExceptionEvent)
		}

		updateLoader(false)
	}

	private fun updateLoader(updateLoader: Boolean) = viewModelState.update {
		it.copy(isLoading = updateLoader)
	}


}