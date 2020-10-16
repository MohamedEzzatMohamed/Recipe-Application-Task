package com.example.recipe_android_app.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_android_app.Model.RecipeItemModel
import com.example.recipe_android_app.Network.RetrofitClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeViewModel : ViewModel() {
    private val retrofitClass = RetrofitClass

    private var _recipeLiveData = MutableLiveData<ArrayList<RecipeItemModel>>()
    val recipeLiveDataModel: LiveData<ArrayList<RecipeItemModel>>
    get() = _recipeLiveData


    fun getRecipe(){
        viewModelScope.launch {
            try {
                val recipeResponse = withContext(Dispatchers.IO){
                    retrofitClass.isDataCorrect<RecipeItemModel>(retrofitClass.apiInterface.getRecipes())
                }

                _recipeLiveData.postValue(recipeResponse)

            } catch (e: Exception){
                Log.e("Error", e.toString())
            }

        }
    }

    //sort fun


}