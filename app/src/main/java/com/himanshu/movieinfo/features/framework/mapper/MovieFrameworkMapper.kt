package com.himanshu.movieinfo.features.framework.mapper

import com.himanshu.movieinfo.features.domain.entity.MoviesEntity
import com.himanshu.movieinfo.features.domain.entity.ResultsEntity
import com.himanshu.movieinfo.features.framework.network.models.remoteentities.MoviesResponse
import com.himanshu.movieinfo.features.framework.network.models.remoteentities.Result
import javax.inject.Inject

class MovieFrameworkMapper @Inject constructor() {

	fun toMoviesEntity(response: MoviesResponse): MoviesEntity {
		val resultEntities = response.results.map { mapResultToEntity(it) }
		return MoviesEntity(response.page, resultEntities)
	}

	private fun mapResultToEntity(result: Result): ResultsEntity {
		return ResultsEntity(
			result.adult,
			result.backdropPath,
			result.genreIds,
			result.id,
			result.originalLanguage,
			result.originalTitle,
			result.overview,
			result.popularity,
			result.posterPath,
			result.releaseDate,
			result.title,
			result.video,
			result.voteAverage,
			result.voteCount
		)
	}

}