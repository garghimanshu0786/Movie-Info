package com.himanshu.movieinfo.base.di

import android.content.Context
import androidx.room.Room
import com.himanshu.movieinfo.features.data.repository.MovieRepository
import com.himanshu.movieinfo.features.data.source.IMovieLocalDataSource
import com.himanshu.movieinfo.features.data.source.IMovieRemoteDataSource
import com.himanshu.movieinfo.features.domain.IMovieRepository
import com.himanshu.movieinfo.features.framework.api.IMovieApiService
import com.himanshu.movieinfo.features.framework.database.AppDatabase
import com.himanshu.movieinfo.features.framework.database.MovieLocalDataSource
import com.himanshu.movieinfo.features.framework.models.databaseentities.PlaylistDao
import com.himanshu.movieinfo.features.framework.network.MovieRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

		@Provides
		@Singleton
		fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
			Room.databaseBuilder(context, AppDatabase::class.java, "movie_info_database.db").build()

		@Provides
		fun providePlaylistDao(database: AppDatabase): PlaylistDao = database.playlistDao()

	}

	@Binds
	abstract fun bindsMovieRepository(movieRepository: MovieRepository): IMovieRepository

	@Binds
	abstract fun bindsMovieRemoteDataSource(movieRemoteDataSource: MovieRemoteDataSource): IMovieRemoteDataSource

	@Binds
	abstract fun bindsMovieLocalDataSource(movieLocalDataSource: MovieLocalDataSource): IMovieLocalDataSource

}