package com.example.recipies

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titles = resources.getStringArray(R.array.recipe_titles)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)

        for ((i,title) in titles.withIndex()) {
            val button = Button(this)
            button.text = title
            button.setBackgroundResource(R.color.grey)
            button.setOnClickListener {
                displayDetailsActivity(title, i)
            }
            list_layout.addView(button)
            var score = sharedPreferences.getInt("$i", -1)
            if (score == 5){
                button.setBackgroundResource(R.color.green)
            } else if (score == 4){
                button.setBackgroundResource(R.color.greenish)
            } else if (score == 3){
                button.setBackgroundResource(R.color.orange)
            } else if (score == 2){
                button.setBackgroundResource(R.color.oranger)
            } else if (score == 1){
                button.setBackgroundResource(R.color.red)
            }
        }
    }

    fun displayDetailsActivity(title: String, index: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("recipe_title", title)
        intent.putExtra("recipe_index", index)
        startActivityForResult(intent, 5)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor =  sharedPreferences.edit()

        val score = data?.getIntExtra("recipe_score", -1)
        val id = data?.getIntExtra("recipe_id", -1)

        if (id != null && score != null && score != 0) {
            editor.putInt("$id",score)
            Toast.makeText(this, "The score was $score", Toast.LENGTH_LONG).show()
        }
        editor.apply()
        editor.commit()

        recreate()
    }
}
