package com.paging3demo.data.dto.books

import com.google.gson.annotations.SerializedName
import com.paging3demo.domain.model.Book
import com.paging3demo.domain.model.BooksResponse

data class BooksResponseDto(
    val kind: String?,
    val totalItems: Int?,

    @SerializedName("items")
    val books: List<Book>?
)

fun BooksResponseDto.toBooks() : List<Book> {
    return books ?: emptyList()
}

fun BooksResponseDto.toBooksResponse() : BooksResponse
{
    return BooksResponse(totalItems = totalItems ?: 0, books = books ?: emptyList())
}