package com.example.nychighschools.model


import com.google.gson.annotations.SerializedName

data class NYCListResponseModelItem(
    @SerializedName("academicopportunities1")
    val academicopportunities1: String,
    @SerializedName("dbn")
    val dbn: String,
    @SerializedName("school_name")
    val schoolName: String=""







    )