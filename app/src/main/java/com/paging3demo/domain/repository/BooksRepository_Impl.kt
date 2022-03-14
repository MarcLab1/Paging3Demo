package com.paging3demo.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.paging3demo.data.BooksApiService
import com.paging3demo.domain.model.Book
import com.paging3demo.domain.paging.BooksPagingSource
import com.paging3demo.util.Constants
import kotlinx.coroutines.flow.Flow

class BooksRepository_Impl(
    val booksApiService: BooksApiService
) : BooksRepository {

    override fun getBooksFlow(query: String): Flow<PagingData<Book>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.BOOKS_PAGE_SIZE),
            pagingSourceFactory = {
                BooksPagingSource(booksApiService = booksApiService, query = query)
            }
        ).flow
    }
}
