package com.example.recipe_android_app.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe_android_app.Model.RecipeItemModel
import com.example.recipe_android_app.R
import com.example.recipe_android_app.interfaces.OnItemClicked
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recipe_item.view.*
import java.util.*
import kotlin.collections.ArrayList


class RecipesListAdapter(
    private var recipesList: ArrayList<RecipeItemModel>,
    private var itemListener: OnItemClicked
) : RecyclerView.Adapter<RecipesListAdapter.RecipesViewHolder>(), Filterable {

    val recipeFullList: ArrayList<RecipeItemModel> = recipesList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val itemview: View = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.recipe_item, parent, false)
        return RecipesViewHolder(itemview)
    }

    //bind the views from viewHolder with the data in arrayList
    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val item = recipesList[position]
        Picasso.get().load(item.thumb)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher).into(holder.recipeImage)
        holder.recipeName.text = item.name
        holder.recipeCal.text = item.calories
        holder.recipeFat.text = item.fats
        holder.recipeCard.setOnClickListener {

            itemListener.onItemClick(item)

        }

    }

    //return num of array size
    override fun getItemCount() = recipesList.size

    //view holder for components in each item in the list
    inner class RecipesViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val recipeImage: ImageView = itemview.recipe_ImageView
        val recipeName: TextView = itemview.recipeName_TextView
        val recipeCal: TextView = itemview.recipeCalNo_TextView
        val recipeFat: TextView = itemview.recipeFatNo_TextView
        val recipeCard: CardView = itemview.recipe_CardView

    }

    //function for handling any data repetition
    fun addRecipe(recipeModelModel: ArrayList<RecipeItemModel>){
        this.recipesList.apply {
            clear()
            addAll(recipeModelModel)
        }
    }

    //filter by searching recipe name
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var recipeFilterList: ArrayList<RecipeItemModel>
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    recipeFilterList = recipeFullList
                } else {
                    val resultList = ArrayList<RecipeItemModel>()
                    for (row in recipeFullList) {
                        if (row.name.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    recipeFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = recipeFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                recipesList = results?.values as ArrayList<RecipeItemModel>
                notifyDataSetChanged()
            }

        }
    }

}