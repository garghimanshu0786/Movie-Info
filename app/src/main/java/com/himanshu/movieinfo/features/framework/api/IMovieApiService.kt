package com.himanshu.movieinfo.features.framework.api

import com.himanshu.movieinfo.features.framework.Constants.API_KEY
import com.himanshu.movieinfo.features.framework.network.models.remoteentities.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface IMovieApiService {

	@GET("popular?api_key=$API_KEY&language=en-US&page=1")
	suspend fun getMovies(): Response<MoviesResponse>

}