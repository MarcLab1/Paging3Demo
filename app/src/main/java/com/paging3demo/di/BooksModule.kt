package com.paging3demo.di

import com.paging3demo.data.BooksApiService
import com.paging3demo.domain.repository.BooksRepository
import com.paging3demo.domain.repository.BooksRepository_Impl
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
object BooksModule {

    @Singleton
    @Provides
    fun providesBooksApiService() : BooksApiService
    {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BOOKS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesBooksRepository(booksApiService: BooksApiService) : BooksRepository
    {
        return BooksRepository_Impl(booksApiService)
    }

}