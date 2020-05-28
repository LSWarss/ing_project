package com.lswarss.ing_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.lswarss.ing_project.db.PostsDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var postsDatabase: PostsDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //This set up the bottom navigation  in our app
        bottomNavigationView.setupWithNavController(fragment.findNavController())

        postsDatabase = PostsDatabase(this)


    }
}
