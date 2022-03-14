package com.paging3demo.domain.repository

import androidx.paging.PagingData
import com.paging3demo.domain.model.Dog
import kotlinx.coroutines.flow.Flow

interface DogRepository {

    fun getDogFlow(): Flow<PagingData<Dog>>
}