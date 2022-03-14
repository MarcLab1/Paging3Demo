package com.paging3demo.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.paging3demo.data.DogApiService
import com.paging3demo.data.dto.dog.toDog
import com.paging3demo.domain.model.Dog
import com.paging3demo.domain.repository.DogRepository

class DogPagingSource(
    private val dogApiService: DogApiService
    ) : PagingSource<Int, Dog>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Dog> {

        return try {
            var currentPage = params.key ?: 1
            val dog = dogApiService.getDog().toDog()

            LoadResult.Page(
                data = listOf(dog),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if(dog.picture.isEmpty()) null else ++currentPage //essentially infinite list as the api should always return a dog
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Dog>): Int? {
        return state.anchorPosition
    }
}
