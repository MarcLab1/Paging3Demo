package com.paging3demo.data.dto.dog

import com.paging3demo.domain.model.Dog

data class DogDto(
    val message: String?,
    val status: String?
)

fun DogDto.toDog() : Dog
{
    return Dog(message ?: "")
}