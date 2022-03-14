package com.paging3demo.domain.model

import com.paging3demo.data.dto.books.AccessInfo
import com.paging3demo.data.dto.books.SaleInfo
import com.paging3demo.data.dto.books.VolumeInfo

data class Book(
    val kind: String?,
    val id: String?,
    val etag: String?,
    val selfLink: String?,
    val volumeInfo: VolumeInfo?,
    val saleInfo: SaleInfo?,
    val accessInfo: AccessInfo?
)