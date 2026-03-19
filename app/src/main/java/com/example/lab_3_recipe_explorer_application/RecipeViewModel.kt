package com.example.lab_3_recipe_explorer_application

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecipeViewModel : ViewModel(){

    //list of recipes used in the app
    val recipes =listOf(
        Recipe(
            id=1,
            title="Spaghetti Bolognese",
            description= "A classic Italian dish with rich meat sauce.",
            detail ="A classic Italian dish with rich meat sauce."
        ),
        Recipe(
            id=2,
            title="Chicken Curry",
            description= "A spicy and savory curry with tender chicken pieces.",
            detail= "A spicy and savory curry with tender chicken pieces."
        ),
        Recipe(
            id=3,
            title="Beef Stroganoff",
            description= "A creamy dish with sauteed pieces of beef and mushrooms.",
            detail= "A creamy dish with sauteed pieces of beef and mushrooms."
        )
    )

    private val _selectedRecipe=MutableStateFlow<Recipe?>(null)
    val selectedRecipe: StateFlow<Recipe?> =_selectedRecipe

    //this function runs when the user clicks a recipe
    //it updates the selected recipe
    fun selectRecipe(recipe: Recipe){
        _selectedRecipe.value=recipe
    }

    fun getRecipeById(recipeId: Int): Recipe?{
        for(recipe in recipes){
            if(recipe.id==recipeId){
                return recipe
            }
        }
        return null
    }
}