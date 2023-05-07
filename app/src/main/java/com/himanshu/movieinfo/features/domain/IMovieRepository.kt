package com.himanshu.movieinfo.features.domain

import com.himanshu.movieinfo.features.domain.entities.MoviesEntity
import com.himanshu.movieinfo.features.domain.entities.ResultEntity

interface IMovieRepository {

	suspend fun getMoviesFromRemote(): ResultEntity<MoviesEntity?>
	suspend fun getPlaylistsByMovieIdFromDb(id: Int): List<String>
	suspend fun addPlaylist(playlistName: String)
	suspend fun addMovieIdToPlaylist(id: Int?, playlistName: String)
	suspend fun removeMovieFromPlaylist(id: Int, playlistName: String)
	suspend fun getAllPlaylists(): List<String>
}