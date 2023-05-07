package com.himanshu.movieinfo.features.framework.models.databaseentities

import androidx.room.TypeConverter

class IntegerListConverter {
	@TypeConverter
	fun fromIntegerList(value: List<Int>?): String? {
		return value?.joinToString(",")
	}

	@TypeConverter
	fun toIntegerList(value: String?): List<Int>? {
		return value?.split(",")?.mapNotNull { it.toIntOrNull() }
	}
}
