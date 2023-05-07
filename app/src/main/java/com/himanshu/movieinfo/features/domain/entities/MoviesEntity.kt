package com.himanshu.movieinfo.features.domain.entities

data class MoviesEntity(
	val movies: List<Movie>
)

data class Movie(
	val id: Int,
	val posterPath: String?,
	val title: String?,
	val voteAverage: Float,
	val playlists: List<String>
)