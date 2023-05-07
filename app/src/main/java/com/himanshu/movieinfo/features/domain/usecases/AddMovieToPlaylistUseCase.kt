package com.himanshu.movieinfo.features.domain.usecases

import com.himanshu.movieinfo.features.domain.IMovieRepository
import com.himanshu.movieinfo.features.domain.entities.MoviesEntity
import javax.inject.Inject

class AddMovieToPlaylistUseCase @Inject constructor(private val movieRepository: IMovieRepository) {

	suspend operator fun invoke(
		id: Int?, playlistName: String, movieEntity: MoviesEntity
	): MoviesEntity {
		if (id != null) {
			val updatedMovies = movieEntity.movies.map { movie ->
				if (movie.id == id && playlistName !in movie.playlists) {
					movieRepository.addMovieIdToPlaylist(id, playlistName)
					movie.copy(playlists = movie.playlists + playlistName)
				} else {
					movie
				}
			}
			return movieEntity.copy(movies = updatedMovies)
		}
		return movieEntity
	}

}
