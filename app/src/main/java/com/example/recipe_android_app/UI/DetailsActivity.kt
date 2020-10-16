package com.example.recipe_android_app.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.recipe_android_app.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //get all the needed data to show details
        val name = intent.getStringExtra("name")
        val image = intent.getStringExtra("image")
        val diff = intent.getStringExtra("diff")
        val fats = intent.getStringExtra("fats")
        val cal = intent.getStringExtra("cal")
        val carb = intent.getStringExtra("carb")
        val pro = intent.getStringExtra("pro")
        val des = intent.getStringExtra("des")
        val time = intent.getStringExtra("time")
        val headline = intent.getStringExtra("headline")


        val recipeDetailImage: ImageView = detailImage_ImageView
        val recipeName: TextView = detailName_TextView
        val recipeCal: TextView = detailCalNo_TextView
        val recipeFat: TextView = detailFatNo_TextView
        val recipeDescription: TextView = detailDescription_TextView
        val recipeTime: TextView = detailTimeNo_TextView
        val recipeProtein: TextView = detailProteinsNo_TextView
        val recipeCarbons: TextView = detailCarbonsNo_TextView
        val recipeDiff: TextView = detailDiffNo_TextView
        val recipeHeadline: TextView = detailHeadlineText_TextView

        recipeName.text = name
        recipeCal.text = cal
        recipeFat.text = fats
        recipeDescription.text = des
        recipeTime.text = time
        recipeProtein.text = pro
        recipeCarbons.text = carb
        recipeDiff.text = diff.toString()
        recipeHeadline.text = headline

        //picasso to handel image lags
        Picasso.get().load(image)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher).into(recipeDetailImage)

    }
}