package com.paging3demo.data.dto.books

data class Offer(
    val finskyOfferType: Int?,
    val listPrice: ListPriceX?,
    val retailPrice: RetailPriceX?,
    val giftable: Boolean?
)