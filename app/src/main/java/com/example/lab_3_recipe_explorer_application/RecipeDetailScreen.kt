package com.example.lab_3_recipe_explorer_application

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    title: String,
    detail:String,
    modifier: Modifier =Modifier
){
    //screen structure(top bar and content area)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text=title)
                }
            )
        },
        modifier = modifier
    ){ innerPadding ->
        //column is used to arrange the content vertically
        Column(
            modifier= Modifier
                .fillMaxSize()
                //prevents the content from overlapping the topAppBar
                .padding(innerPadding)
                //additional padding
                .padding(16.dp)
        ){
            //shows the detailed information about the recipe
            Text(
                text =detail,
                style= MaterialTheme.typography.bodyLarge
            )
        }
    }
}
