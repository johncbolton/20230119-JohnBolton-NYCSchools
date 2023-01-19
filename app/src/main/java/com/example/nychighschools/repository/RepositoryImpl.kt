package com.example.nychighschools.repository


import com.example.nychighschools.data.APIDetails
import com.example.nychighschools.model.NYCDetailResponseModel
import com.example.nychighschools.model.NYCListResponseModel
import com.example.nychighschools.repository.Repository
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val fetchAPI: APIDetails) : Repository {
    override suspend fun getNYCSchools(): NYCListResponseModel =
        fetchAPI.getNYCSchools()

    override suspend fun getSchoolDetails(schoolId: String): Response<NYCDetailResponseModel> =
        fetchAPI.getSchoolDetails(schoolId)
}