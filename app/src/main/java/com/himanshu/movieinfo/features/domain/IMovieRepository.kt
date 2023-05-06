package com.himanshu.movieinfo.features.domain

import com.himanshu.movieinfo.features.domain.entity.MoviesEntity
import com.himanshu.movieinfo.features.domain.entity.ResultEntity

interface IMovieRepository {

	suspend fun getMoviesFromRemote(): ResultEntity<MoviesEntity?>

}