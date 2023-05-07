package com.himanshu.movieinfo.features.framework.models.databaseentities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PlaylistDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun addPlaylist(playlist: PlaylistEntity)

	@Query("SELECT name FROM playlists")
	suspend fun getAllPlaylists(): List<String>

	@Update
	suspend fun updatePlaylist(playlist: PlaylistEntity)

	@Query("SELECT name FROM playlists WHERE movieIds LIKE '%' || :movieId || '%'")
	suspend fun getPlaylistsByMovieId(movieId: Int): List<String>

	@Query("SELECT * FROM playlists WHERE name = :name")
	suspend fun getPlaylistByName(name: String): PlaylistEntity?
}
