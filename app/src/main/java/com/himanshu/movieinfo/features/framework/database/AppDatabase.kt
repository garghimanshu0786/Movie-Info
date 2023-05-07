package com.himanshu.movieinfo.features.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.himanshu.movieinfo.features.framework.models.databaseentities.PlaylistDao
import com.himanshu.movieinfo.features.framework.models.databaseentities.PlaylistEntity

@Database(entities = [PlaylistEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
	abstract fun playlistDao(): PlaylistDao
}