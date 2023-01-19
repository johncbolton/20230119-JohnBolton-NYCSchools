package com.example.nychighschools.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nychighschools.components.AppDivider
import com.example.nychighschools.components.ShowProgressIndicator
import com.example.nychighschools.model.NYCListResponseModelItem
import com.example.nychighschools.ui.screens.common.MainViewModel

@Composable
fun NYCSchoolsList(
    viewModel: MainViewModel = hiltViewModel(),
    selectedUser: (String) -> Unit
) {

    val uiState = viewModel.uiState

    ShowProgressIndicator(isLoadingData = uiState.isFetchingSchools)

    UsersList(uiState.schoolsList, selectedUser)
}

@Composable
fun UsersList(schoolsList: List<NYCListResponseModelItem>, selectedschools: (String) -> Unit) {
    LazyColumn {
        itemsIndexed(items = schoolsList) { index, item ->
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            SchoolsItem(schoolsList = item, selectedSchool = selectedschools)
            AppDivider(verticalPadding = 8.dp)
        }
    }
}


@Composable
fun SchoolsItem(schoolsList: NYCListResponseModelItem, selectedSchool: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .clickable(onClick = { selectedSchool(schoolsList.dbn) })
            .fillMaxWidth()
            .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Surface() {

            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = schoolsList.dbn,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = schoolsList.schoolName,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .background(
                                Color.LightGray
                            )
                            .padding(4.dp)
                    )
                    Text(
                        text = schoolsList.schoolName,
                        style = MaterialTheme.typography.body1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                }
            }
        }
    }

}