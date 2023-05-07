package com.himanshu.movieinfo.features.framework.database

import com.himanshu.movieinfo.features.data.source.IMovieLocalDataSource
import com.himanshu.movieinfo.features.framework.models.databaseentities.PlaylistDao
import com.himanshu.movieinfo.features.framework.models.databaseentities.PlaylistEntity
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(private val playlistDao: PlaylistDao) :
	IMovieLocalDataSource {

	override suspend fun getPlaylistsByMovieId(id: Int) = playlistDao.getPlaylistsByMovieId(id)

	override suspend fun addPlaylist(playlistName: String) =
		playlistDao.addPlaylist(PlaylistEntity(playlistName, emptyList()))

	override suspend fun addMovieIdToPlaylist(id: Int?, playlistName: String) {
		id ?: return

		val existingPlaylist = playlistDao.getPlaylistByName(playlistName)
		if (existingPlaylist != null && id !in existingPlaylist.movieIds) {
			val updatedMovieIds = existingPlaylist.movieIds.toMutableList().apply {
				add(id)
			}
			val updatedPlaylist = existingPlaylist.copy(movieIds = updatedMovieIds)
			playlistDao.updatePlaylist(updatedPlaylist)
		} else {
			addPlaylist(playlistName)
		}
	}

	override suspend fun removeMovieFromPlaylist(id: Int, playlistName: String) {
		val existingPlaylist = playlistDao.getPlaylistByName(playlistName)
		if (existingPlaylist != null && id in existingPlaylist.movieIds) {
			val updatedMovieIds = existingPlaylist.movieIds.toMutableList().apply {
				remove(id)
			}
			val updatedPlaylist = existingPlaylist.copy(movieIds = updatedMovieIds)
			playlistDao.updatePlaylist(updatedPlaylist)
		}
	}

	override suspend fun getAllPlaylists() = playlistDao.getAllPlaylists()
}