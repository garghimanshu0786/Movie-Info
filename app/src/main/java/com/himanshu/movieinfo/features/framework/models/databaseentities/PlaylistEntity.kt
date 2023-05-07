package com.himanshu.movieinfo.features.framework.models.databaseentities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "playlists")
@TypeConverters(IntegerListConverter::class)
data class PlaylistEntity(
	@PrimaryKey val name: String,
	val movieIds: List<Int>
)
