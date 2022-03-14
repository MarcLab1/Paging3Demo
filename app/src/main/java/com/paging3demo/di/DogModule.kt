package com.paging3demo.di

import com.paging3demo.data.DogApiService
import com.paging3demo.domain.repository.DogRepository
import com.paging3demo.domain.repository.DogRepository_Impl
import com.paging3demo.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DogModule {

    @Provides
    @Singleton
    fun provideApiService(): DogApiService {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.DOG_BASE_URL).build().create(DogApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDogRepository(apiService: DogApiService) : DogRepository {
        return DogRepository_Impl(apiService)
    }
}