package com.himanshu.movieinfo.features.domain.usecases

import com.himanshu.movieinfo.features.domain.IMovieRepository
import com.himanshu.movieinfo.features.domain.entity.MoviesEntity
import com.himanshu.movieinfo.features.domain.entity.ResultEntity
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieRepository: IMovieRepository) {

	suspend operator fun invoke(): ResultEntity<MoviesEntity?> {
		val response: ResultEntity<MoviesEntity?> = movieRepository.getMoviesFromRemote()
		var result: ResultEntity<MoviesEntity?>? = null
		if (response is ResultEntity.Success) {
			val modifiedMovies =
				response.data?.results?.map { it.copy(posterPath = IMAGE_BASE_PATH + it.posterPath) }
					?: emptyList()
			result = ResultEntity.Success(response.data?.copy(results = modifiedMovies))
		}
		return result ?: response
	}

	companion object {
		const val IMAGE_BASE_PATH = "https://image.tmdb.org/t/p/w500"
	}
}
