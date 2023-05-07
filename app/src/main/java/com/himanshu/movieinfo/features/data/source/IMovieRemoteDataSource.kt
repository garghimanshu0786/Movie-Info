package com.himanshu.movieinfo.features.data.source

import com.himanshu.movieinfo.features.domain.entities.MoviesEntity
import com.himanshu.movieinfo.features.domain.entities.ResultEntity

interface IMovieRemoteDataSource {

	suspend fun getMovies(): ResultEntity<MoviesEntity?>
}