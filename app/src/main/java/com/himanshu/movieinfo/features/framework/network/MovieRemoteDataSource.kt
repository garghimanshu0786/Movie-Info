package com.himanshu.movieinfo.features.framework.network

import com.himanshu.movieinfo.features.framework.utilities.callAPI
import com.himanshu.movieinfo.features.data.source.IMovieRemoteDataSource
import com.himanshu.movieinfo.features.domain.entities.MoviesEntity
import com.himanshu.movieinfo.features.domain.entities.ResultEntity
import com.himanshu.movieinfo.features.framework.api.IMovieApiService
import com.himanshu.movieinfo.features.framework.mapper.MovieFrameworkMapper
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
	private val movieApiService: IMovieApiService, private val mapper: MovieFrameworkMapper
) : IMovieRemoteDataSource {

	override suspend fun getMovies(): ResultEntity<MoviesEntity?> =
		callAPI(movieApiService::getMovies) { it?.let { response -> mapper.toMoviesEntity(response) } }
}