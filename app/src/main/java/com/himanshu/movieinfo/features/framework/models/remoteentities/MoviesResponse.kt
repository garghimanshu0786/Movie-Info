package com.himanshu.movieinfo.features.framework.models.remoteentities

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
	val results: List<Result>
)

data class Result(
	val id: Int,
	@SerializedName("poster_path") val posterPath: String?,
	val title: String?,
	@SerializedName("vote_average") val voteAverage: Float,
)