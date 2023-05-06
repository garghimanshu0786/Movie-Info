package com.himanshu.movieinfo.base.di

import com.himanshu.movieinfo.features.data.repository.MovieRepository
import com.himanshu.movieinfo.features.data.source.IMovieRemoteDataSource
import com.himanshu.movieinfo.features.domain.IMovieRepository
import com.himanshu.movieinfo.features.framework.api.IMovieApiService
import com.himanshu.movieinfo.features.framework.network.MovieRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieInfoModule {

	companion object {
		@Singleton
		@Provides
		fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

		@Singleton
		@Provides
		fun provideRetrofit(
			okHttpClient: OkHttpClient
		): Retrofit =
			Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/movie/").client(okHttpClient)
				.addConverterFactory(GsonConverterFactory.create()).build()

		@Provides
		@Singleton
		fun provideMovieApiService(retrofit: Retrofit): IMovieApiService =
			retrofit.create(IMovieApiService::class.java)

	}

	@Binds
	abstract fun bindsMovieRepository(movieRepository: MovieRepository): IMovieRepository

	@Binds
	abstract fun bindsMovieRemoteDataSource(movieRemoteDataSource: MovieRemoteDataSource): IMovieRemoteDataSource

}