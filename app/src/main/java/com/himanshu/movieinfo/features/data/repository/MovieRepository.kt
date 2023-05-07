package com.himanshu.movieinfo.features.data.repository

import com.himanshu.movieinfo.features.data.source.IMovieLocalDataSource
import com.himanshu.movieinfo.features.data.source.IMovieRemoteDataSource
import com.himanshu.movieinfo.features.domain.IMovieRepository
import com.himanshu.movieinfo.features.domain.entities.MoviesEntity
import com.himanshu.movieinfo.features.domain.entities.ResultEntity
import javax.inject.Inject

class MovieRepository @Inject constructor(
	private val remoteDataSource: IMovieRemoteDataSource,
	private val localDataSource: IMovieLocalDataSource,
) : IMovieRepository {
	override suspend fun getMoviesFromRemote(): ResultEntity<MoviesEntity?> =
		remoteDataSource.getMovies()

	override suspend fun getPlaylistsByMovieIdFromDb(id: Int): List<String> =
		localDataSource.getPlaylistsByMovieId(id)

	override suspend fun addPlaylist(playlistName: String) =
		localDataSource.addPlaylist(playlistName)

	override suspend fun addMovieIdToPlaylist(id: Int?, playlistName: String) =
		localDataSource.addMovieIdToPlaylist(id, playlistName)

	override suspend fun removeMovieFromPlaylist(id: Int, playlistName: String) =
		localDataSource.removeMovieFromPlaylist(id, playlistName)

	override suspend fun getAllPlaylists(): List<String> = localDataSource.getAllPlaylists()
}