package com.himanshu.movieinfo.features.data.repository

import com.himanshu.movieinfo.features.data.source.IMovieRemoteDataSource
import com.himanshu.movieinfo.features.domain.IMovieRepository
import com.himanshu.movieinfo.features.domain.entity.MoviesEntity
import com.himanshu.movieinfo.features.domain.entity.ResultEntity
import javax.inject.Inject

class MovieRepository @Inject constructor(
	private val remoteDataSource: IMovieRemoteDataSource,
) : IMovieRepository {
	override suspend fun getMoviesFromRemote(): ResultEntity<MoviesEntity?> =
		remoteDataSource.getMovies()
}