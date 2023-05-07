package com.himanshu.movieinfo.features.data.source

interface IMovieLocalDataSource {

	suspend fun getPlaylistsByMovieId(id: Int): List<String>

	suspend fun addPlaylist(playlistName: String)

	suspend fun addMovieIdToPlaylist(id: Int?, playlistName: String)

	suspend fun removeMovieFromPlaylist(id: Int, playlistName: String)

	suspend fun getAllPlaylists(): List<String>
}
