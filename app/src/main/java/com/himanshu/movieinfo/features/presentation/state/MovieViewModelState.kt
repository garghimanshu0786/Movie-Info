package com.himanshu.movieinfo.features.presentation.state

data class MovieViewModelState(
	val isLoading: Boolean = false,
	val movies: List<MovieViewData> = emptyList(),
) {
	fun toUiState() = MovieUIState(
		isLoading,
		movies,
	)
}

data class MovieUIState(
	val isLoading: Boolean,
	val movies: List<MovieViewData>
)

