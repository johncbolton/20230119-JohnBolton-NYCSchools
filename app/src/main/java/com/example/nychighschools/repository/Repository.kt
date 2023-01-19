package com.example.nychighschools.repository


import com.example.nychighschools.model.NYCDetailResponseModel
import com.example.nychighschools.model.NYCListResponseModel
import retrofit2.Response

interface Repository {
    suspend fun getNYCSchools(): NYCListResponseModel

    suspend fun getSchoolDetails(schoolId: String): Response<NYCDetailResponseModel>
}