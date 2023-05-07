package com.himanshu.movieinfo.features.framework.mapper

import com.himanshu.movieinfo.features.domain.entities.Movie
import com.himanshu.movieinfo.features.domain.entities.MoviesEntity
import com.himanshu.movieinfo.features.framework.models.remoteentities.MoviesResponse
import com.himanshu.movieinfo.features.framework.models.remoteentities.Result
import javax.inject.Inject

class MovieFrameworkMapper @Inject constructor() {

	fun toMoviesEntity(response: MoviesResponse): MoviesEntity {
		val resultEntities = response.results.map { mapResultToEntity(it) }
		return MoviesEntity(resultEntities)
	}

	private fun mapResultToEntity(result: Result): Movie {
		return Movie(
			result.id,
			result.posterPath,
			result.title,
			result.voteAverage,
			emptyList()
		)
	}

}