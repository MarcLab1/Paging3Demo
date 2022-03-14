package com.paging3demo.data.dto.books

import com.paging3demo.domain.model.Book

data class BookResponseDto(
    val kind: String?,
    val id: String?,
    val etag: String?,
    val selfLink: String?,
    val volumeInfo: VolumeInfo?,
    val saleInfo: SaleInfo?,
    val accessInfo: AccessInfo?
)

fun BookResponseDto.toBook(): Book {
    return Book(
        kind,
        id,
        etag,
        selfLink,
        volumeInfo,
        saleInfo,
        accessInfo
    )
}