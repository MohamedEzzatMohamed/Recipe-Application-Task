package com.example.recipe_android_app.Model

data class RecipeModel(val recipeItemModel: ArrayList<RecipeItemModel>)

data class RecipeItemModel(
    val calories: String,
    val carbos: String,
    val country: String,
    val description: String,
    val difficulty: Int,
    val fats: String,
    val headline: String,
    val id: String,
    val image: String,
    val name: String,
    val proteins: String,
    val thumb: String,
    val time: String
)