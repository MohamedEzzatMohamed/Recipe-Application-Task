package com.example.recipe_android_app.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipe_android_app.Adapter.RecipesListAdapter
import com.example.recipe_android_app.Model.RecipeItemModel
import com.example.recipe_android_app.R
import com.example.recipe_android_app.ViewModel.RecipeViewModel
import com.example.recipe_android_app.interfaces.OnItemClicked
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemClicked {

    private lateinit var viewModel: RecipeViewModel
    private lateinit var recipesListAdapter: RecipesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        viewModel.getRecipe()

        setupObserver()

        setSpinner()

    }

    //setup the spinner
    private fun setSpinner(){

        //spinner
        val spinner: Spinner = sort_Spinner
        val spinnerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.sort_status,
            android.R.layout.simple_spinner_item
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.setAdapter(spinnerAdapter)
        spinner.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    // do nothing
                } else {
                    //should sort the list with what have been chosen descending order or ascending order

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
    }

    //setup the observer to get the data from the api
    private fun setupObserver(){
        viewModel.recipeLiveDataModel.observe(this, Observer { recipeList ->
            if (recipeList != null) {
                setRecipeRecyclerView()
                retrieveRecipeList(recipeList)
            }
        })
    }

    //set the main parameters for the recyclerView with the adapter
    private fun setRecipeRecyclerView() {
        recipe_RecyclerView.layoutManager = LinearLayoutManager(this)
        recipesListAdapter = RecipesListAdapter(arrayListOf(), this)
        recipe_RecyclerView.addItemDecoration(
            DividerItemDecoration(
                recipe_RecyclerView.context,
                (recipe_RecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recipe_RecyclerView.adapter = recipesListAdapter
    }

    //get the recipe list from ViewModel
    private fun retrieveRecipeList(recipeModelModel: ArrayList<RecipeItemModel>) {
        recipesListAdapter.apply {
            addRecipe(recipeModelModel)
            notifyDataSetChanged()
        }
    }

    //add a menu option for searching in the list using name
    //make the actionbar a textView and you can input a text in it
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.d("onCreateOptionsMenu", "onCreateOptionsMenu: filter")
        menuInflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                recipesListAdapter.filter.filter(newText)
                return true
            }
        })
        return true
    }

    //item click to go for details activity
    override fun onItemClick(recipeModel: RecipeItemModel) {
        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
        intent.putExtra("name", recipeModel.name)
        intent.putExtra("image", recipeModel.image)
        intent.putExtra("diff", recipeModel.difficulty)
        intent.putExtra("fats", recipeModel.fats)
        intent.putExtra("cal", recipeModel.calories)
        intent.putExtra("carb", recipeModel.carbos)
        intent.putExtra("pro", recipeModel.proteins)
        intent.putExtra("des", recipeModel.description)
        intent.putExtra("time", recipeModel.time)
        intent.putExtra("headline", recipeModel.headline)


        startActivity(intent)
//       Toast.makeText(this, "${recipeModel.description}", Toast.LENGTH_LONG).show()
    }
}