package com.example.recipies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val recipes = resources.getStringArray(R.array.recipe_values)

        val title = intent.getStringExtra("recipe_title")
        index = intent.getIntExtra("recipe_index", -1)

        details_title.text = title
        details_content.text = recipes[index]
    }

    fun saveButtonClicked(view: View) {
        val score = details_ratingbar.rating.toInt()
        val resultIntent = Intent()
        resultIntent.putExtra("recipe_score", score)
        resultIntent.putExtra("recipe_id", index)
        setResult(10, resultIntent)
        finish()
    }
}
