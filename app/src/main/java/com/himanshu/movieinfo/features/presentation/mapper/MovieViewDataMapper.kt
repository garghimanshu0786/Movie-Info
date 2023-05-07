package com.himanshu.movieinfo.features.presentation.mapper

import com.himanshu.movieinfo.features.domain.entities.Movie
import com.himanshu.movieinfo.features.domain.entities.MoviesEntity
import com.himanshu.movieinfo.features.presentation.state.MovieViewData
import javax.inject.Inject

class MovieViewDataMapper @Inject constructor() {
	fun mapMoviesEntityToViewData(entity: MoviesEntity?): List<MovieViewData> {
		return entity?.movies?.map { mapResultEntityToViewData(it) } ?: emptyList()
	}

	private fun mapResultEntityToViewData(movie: Movie): MovieViewData {
		return MovieViewData(
			id = movie.id,
			name = movie.title ?: "",
			rating = movie.voteAverage.toString(),
			image = movie.posterPath ?: "",
			playlists = movie.playlists
		)
	}

	fun mapViewDataToMoviesEntity(movies: List<MovieViewData>) = MoviesEntity(movies.map {
		with(it) {
			Movie(
				id = id, posterPath = image, title = name, voteAverage = rating.toFloat(), playlists
			)
		}
	})

}