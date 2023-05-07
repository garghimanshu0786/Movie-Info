package com.himanshu.movieinfo.features.domain.usecases

import com.himanshu.movieinfo.features.domain.IMovieRepository
import javax.inject.Inject

class GetAllPlaylistsUseCase @Inject constructor(private val movieRepository: IMovieRepository) {

	suspend operator fun invoke(): List<String> = movieRepository.getAllPlaylists()

}
