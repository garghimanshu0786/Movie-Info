package com.himanshu.movieinfo.features.presentation.state

data class MovieViewData(
	val id: Int,
	val name: String,
	val rating: String,
	val image: String,
	val playlists: List<String>
)