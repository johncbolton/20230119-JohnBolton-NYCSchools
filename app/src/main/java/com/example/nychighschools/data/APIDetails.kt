package com.example.nychighschools.data


import com.example.nychighschools.model.NYCDetailResponseModel
import com.example.nychighschools.model.NYCListResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface APIDetails {
    @GET("s3k6-pzi2.json")
    suspend fun getNYCSchools(): NYCListResponseModel

    @GET("f9bf-2cp4.json")
    suspend fun getSchoolDetails(
        @Query("dbn") schoolId: String,
    ): Response<NYCDetailResponseModel>
}
