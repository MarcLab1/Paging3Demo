package com.paging3demo.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.paging3demo.data.DogApiService
import com.paging3demo.domain.model.Dog
import com.paging3demo.domain.paging.DogPagingSource
import com.paging3demo.util.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DogRepository_Impl @Inject constructor(
    private val dogApiService: DogApiService
) : DogRepository {

    override fun getDogFlow(): Flow<PagingData<Dog>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.DOG_PAGE_SIZE),
            pagingSourceFactory = {
                DogPagingSource(dogApiService = dogApiService)
            }
        ).flow
    }
}