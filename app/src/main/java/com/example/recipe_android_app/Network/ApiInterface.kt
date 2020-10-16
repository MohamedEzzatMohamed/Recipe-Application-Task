package com.example.recipe_android_app.Network

import com.example.recipe_android_app.Model.RecipeItemModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    //get all recipes
    @GET("recipes.json")
    suspend fun getRecipes(): Response<ArrayList<RecipeItemModel>>
}