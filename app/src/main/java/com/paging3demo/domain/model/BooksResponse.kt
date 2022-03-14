package com.paging3demo.domain.model

data class BooksResponse(val totalItems: Int,
                         val books: List<Book>)