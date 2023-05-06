package com.himanshu.movieinfo.features.domain.entity

data class MoviesEntity(
	val page: Int,
	val results: List<ResultsEntity>
)

data class ResultsEntity(
	val adult: String?,
	val backdropPath: String?,
	val genreIds: List<Int>?,
	val id: Int,
	val originalLanguage: String?,
	val originalTitle: String?,
	val overview: String?,
	val popularity: String?,
	val posterPath: String?,
	val releaseDate: String?,
	val title: String?,
	val video: Boolean,
	val voteAverage: Float,
	val voteCount: Int

)