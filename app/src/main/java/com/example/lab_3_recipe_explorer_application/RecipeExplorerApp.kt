package com.example.lab_3_recipe_explorer_application

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun RecipeExplorerApp(
    windowSize:WindowWidthSizeClass,
    navController: NavHostController=rememberNavController()
) {
    //gets the viewModel
    val viewModel: RecipeViewModel= viewModel()
    //observes the selected recipe from the stateflow
    val selectedRecipe by viewModel.selectedRecipe.collectAsState()

    when(windowSize){
        //phone layout
        WindowWidthSizeClass.Compact -> {
            Scaffold { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "list",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp)
                ) {
                    //route for the recipe list screen
                    composable("list") {
                        RecipeListScreen(
                            title="Recipe List",
                            recipes = viewModel.recipes,
                            onRecipeClick = { recipe ->
                                //saves the selected recipe in the viewModel
                                viewModel.selectRecipe(recipe)
                                //goes to detail screen and passes recipe id.
                                navController.navigate("detail/${recipe.id}")
                            }
                        )
                    }
                    //rote for the detail screen
                    composable(
                        route = "detail/{recipeId}",
                        arguments = listOf(
                            navArgument("recipeId") {
                                type = NavType.IntType
                            }
                        )
                    ) { backStackEntry ->
                        //gets the recipe id from navigation
                        val recipeId = backStackEntry.arguments?.getInt("recipeId")
                        var recipe: Recipe? = null
                        //finds the recipe with that id
                        if (recipeId != null) {
                            recipe = viewModel.getRecipeById(recipeId)
                        }
                        if (recipe != null) {
                            RecipeDetailScreen(
                                title = recipe.title,
                                detail = recipe.detail
                            )
                        }
                    }
                }
            }
        }

        //medium and expanded screen uses tablet Layout
        WindowWidthSizeClass.Medium,
        WindowWidthSizeClass.Expanded -> {
            Row(
                modifier=Modifier.fillMaxSize()
                    .padding(16.dp)
            ){
                //left side shows the recipe list
                RecipeListScreen(
                    title="Recipe List",
                    recipes=viewModel.recipes,
                    onRecipeClick= { recipe ->
                        viewModel.selectRecipe(recipe)
                    },
                    modifier = Modifier.weight(1f)
                )
                //right side shows detail or text
                val recipe=selectedRecipe
                if(recipe !=null){
                    RecipeDetailScreen(
                        title =recipe.title,
                        detail=recipe.detail,
                        modifier=Modifier.weight(1f)
                    )
                } else{
                    RecipeDetailScreen(
                        title="Recipe Detail",
                        detail="Please select a recipe.",
                        modifier=Modifier.weight(1f)
                    )
                }
            }
        }
        else -> {}
    }
}
//previews
@Preview(showBackground = true)
@Composable
fun RecipeAppPhonePreview(){
    RecipeExplorerApp(
        windowSize = WindowWidthSizeClass.Compact
    )
}

@Preview(showBackground = true, widthDp = 900, heightDp = 600)
@Composable
fun RecipeAppTabletPreview(){
    RecipeExplorerApp(
        windowSize = WindowWidthSizeClass.Expanded
    )
}