package com.paging3demo.domain.repository

import androidx.paging.PagingData
import com.paging3demo.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    fun getBooksFlow(query: String): Flow<PagingData<Book>>
}