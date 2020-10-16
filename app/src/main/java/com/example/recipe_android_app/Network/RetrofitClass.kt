package com.example.recipe_android_app.Network

import android.util.Log
import com.example.recipe_android_app.Model.RecipeItemModel
import com.example.recipe_android_app.Utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClass {

    private fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInterface: ApiInterface = getInstance().create(ApiInterface::class.java)

    fun <T: Any> isDataCorrect(response: Response<ArrayList<RecipeItemModel>>): ArrayList<RecipeItemModel>? {
        when {
            response.isSuccessful -> return response.body()

            response.code() == Constant.INTERNAL_SERVER_ERROR ->
              Log.e("Error", response.code().toString())

            else ->
                Log.e("Error", response.code().toString())

        }

        return response.body()
    }
}