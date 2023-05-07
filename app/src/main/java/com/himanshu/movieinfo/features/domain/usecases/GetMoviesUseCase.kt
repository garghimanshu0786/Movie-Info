package com.himanshu.movieinfo.features.domain.usecases

import com.himanshu.movieinfo.features.domain.IMovieRepository
import com.himanshu.movieinfo.features.domain.entities.MoviesEntity
import com.himanshu.movieinfo.features.domain.entities.ResultEntity
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieRepository: IMovieRepository) {

	suspend operator fun invoke(): ResultEntity<MoviesEntity?> {
		val response: ResultEntity<MoviesEntity?> = movieRepository.getMoviesFromRemote()
		return if (response is ResultEntity.Success) {
			val modifiedMovies = response.data?.movies?.map {
				it.copy(
					posterPath = IMAGE_BASE_URL + it.posterPath,
					playlists = movieRepository.getPlaylistsByMovieIdFromDb(it.id)
				)
			} ?: emptyList()
			ResultEntity.Success(response.data?.copy(movies = modifiedMovies))
		} else {
			response
		}
	}

	companion object {
		const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
	}
}
