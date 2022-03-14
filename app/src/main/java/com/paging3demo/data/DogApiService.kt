package com.paging3demo.data

import com.paging3demo.data.dto.dog.DogDto
import retrofit2.http.GET

interface DogApiService {

    @GET("/api/breeds/image/random")
    suspend fun getDog() : DogDto
}