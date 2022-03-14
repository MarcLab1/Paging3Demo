package com.paging3demo.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.paging3demo.data.BooksApiService
import com.paging3demo.data.dto.books.toBooksResponse
import com.paging3demo.domain.model.Book
import com.paging3demo.util.Constants
import retrofit2.HttpException
import java.io.IOException

class BooksPagingSource(
    private val booksApiService: BooksApiService,
    private val query: String
) : PagingSource<Int, Book>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {

        return try {
            val currentPage = params.key ?: 0 //startIndex begins at zero
            val booksResponse = booksApiService.getBooksByStartIndex(
                query = if (query.isEmpty()) Constants.DEFAULT_SEARCH_QUERY else query,
                startIndex = (currentPage * Constants.BOOKS_PAGE_SIZE).toString()
            ).toBooksResponse()

            LoadResult.Page(
                data = booksResponse.books,
                prevKey = if (currentPage == 0) null else currentPage - 1,
                nextKey = if (booksResponse.books == null || booksResponse.books.isEmpty()) null else currentPage + 1
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition
    }
}
