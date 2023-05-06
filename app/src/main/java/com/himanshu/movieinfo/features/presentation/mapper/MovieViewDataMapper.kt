package com.himanshu.movieinfo.features.presentation.mapper

import com.himanshu.movieinfo.features.domain.entity.MoviesEntity
import com.himanshu.movieinfo.features.domain.entity.ResultsEntity
import com.himanshu.movieinfo.features.presentation.state.MovieViewData
import javax.inject.Inject

class MovieViewDataMapper @Inject constructor() {
	fun mapMoviesEntityToViewData(entity: MoviesEntity?): List<MovieViewData> {
		return entity?.results?.map { mapResultEntityToViewData(it) } ?: emptyList()
	}

	private fun mapResultEntityToViewData(entity: ResultsEntity): MovieViewData {
		return MovieViewData(
			name = entity.title ?: "",
			rating = entity.voteAverage.toString(),
			image = entity.posterPath ?: "",
			playlists = emptyList()
		)
	}

}