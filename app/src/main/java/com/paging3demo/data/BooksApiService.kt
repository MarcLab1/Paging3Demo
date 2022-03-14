package com.paging3demo.data

import com.paging3demo.data.dto.books.BookResponseDto
import com.paging3demo.data.dto.books.BooksResponseDto
import com.paging3demo.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {

    @GET("volumes")
    suspend fun getBooksByStartIndex(
        @Query("q") query: String,
        @Query("startIndex") startIndex : String = "0",
        @Query("key") key: String = Constants.API_KEY,
    ) : BooksResponseDto

    @GET("volumes/{volumeId}")
    suspend fun getBookByVolumeId(
        @Path("volumeId") volumeId: String,
        @Query("key") key: String = Constants.API_KEY
    ) : BookResponseDto


}