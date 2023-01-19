package com.example.nychighschools.ui.screens.common

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nychighschools.model.NYCDetailResponseModel
import com.example.nychighschools.model.NYCListResponseModel
import com.example.nychighschools.model.NYCListResponseModelItem
import com.example.nychighschools.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor (private val repository: Repository): ViewModel() {
    var uiState by mutableStateOf(HomeUiState())
        private set

    var _NYCSchoolAPIData = mutableStateOf(NYCListResponseModel())
    val nYCSchoolAPIData: State<NYCListResponseModel> get() = _NYCSchoolAPIData

    val _schoolDetailsApiData = mutableStateOf(NYCDetailResponseModel())
    val schoolDetailsApiData: State<NYCDetailResponseModel> get() = _schoolDetailsApiData

    init {
        viewModelScope.launch {
            try {
                startFetchingNYCSchoolData()
            } catch (e: IOException) {
                Log.e("Error", "$e")
            }
        }
    }




    fun getNYCSchoolDetails(schoolId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getSchoolDetails(schoolId = schoolId)
            if(response.isSuccessful) {
                response.body()?.let {
                    _schoolDetailsApiData.value = it
                }
            }
        }
    }

    private suspend fun startFetchingNYCSchoolData() {
        uiState = HomeUiState(isFetchingSchools = true)
        uiState = HomeUiState(
            schoolsList = repository.getNYCSchools() as List<NYCListResponseModelItem>,
            isFetchingSchools = false
        )
    }
}

data class HomeUiState(
    var schoolsList: List<NYCListResponseModelItem> = emptyList(),
    val isFetchingSchools: Boolean = false,
)