package com.example.worldcupapp.di

import com.example.worldcupapp.data.remote.CricketApiService
import com.example.worldcupapp.data.remote.MockLiveDataSource
import com.example.worldcupapp.data.repository.MatchRepositoryImpl
import com.example.worldcupapp.data.repository.PlayerRepositoryImpl
import com.example.worldcupapp.domain.repository.MatchRepository
import com.example.worldcupapp.domain.repository.PlayerRepository
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
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("x-rapidapi-key", "058c83be4cmsh6b455f098c4da69p1d4f5ejsnb2dfc0953659")
                    .addHeader("x-rapidapi-host", "cricket-api-free-data.p.rapidapi.com")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideCricketApi(okHttpClient: OkHttpClient): CricketApiService {
        return Retrofit.Builder()
            .baseUrl("https://cricket-api-free-data.p.rapidapi.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CricketApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMockDataSource(): MockLiveDataSource {
        return MockLiveDataSource()
    }

    @Provides
    @Singleton
    fun provideMatchRepository(dataSource: MockLiveDataSource, api: CricketApiService): MatchRepository {
        return MatchRepositoryImpl(dataSource, api)
    }

    @Provides
    @Singleton
    fun providePlayerRepository(api: CricketApiService): PlayerRepository {
        return PlayerRepositoryImpl(api)
    }
}
