package com.himanshu.movieinfo.features.domain.usecases

import com.himanshu.movieinfo.features.domain.IMovieRepository
import javax.inject.Inject

class AddPlaylistUseCase @Inject constructor(private val movieRepository: IMovieRepository) {

	suspend operator fun invoke(
		playlistName: String,
		allPlayLists: List<String>
	): List<String> {
		val playlists: MutableList<String> = allPlayLists.toMutableList()
		if (allPlayLists.contains(playlistName).not()) {
			movieRepository.addPlaylist(playlistName)
			playlists.add(playlistName)
		}
		return playlists
	}

}
