package com.himanshu.movieinfo.features.presentation.state

data class MovieViewModelState(
	val isLoading: Boolean = false,
	val movies: List<MovieViewData> = emptyList(),
	val selectedMovieId: Int? = null,
	val filterPlaylistName: String? = null,
	val allPlayLists: List<String> = mutableListOf(),
) {
	fun toUiState() = MovieUIState(
		isLoading, movies, selectedMovieId, filterPlaylistName, allPlayLists
	)
}

data class MovieUIState(
	val isLoading: Boolean,
	val movies: List<MovieViewData>,
	val selectedMovieId: Int?,
	val filterPlaylistName: String?,
	val allPlayLists: List<String>,
)