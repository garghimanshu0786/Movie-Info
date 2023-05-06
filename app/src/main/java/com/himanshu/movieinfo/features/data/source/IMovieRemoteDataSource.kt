package com.himanshu.movieinfo.features.data.source

import com.himanshu.movieinfo.features.domain.entity.MoviesEntity
import com.himanshu.movieinfo.features.domain.entity.ResultEntity

interface IMovieRemoteDataSource {

	suspend fun getMovies(): ResultEntity<MoviesEntity?>
}