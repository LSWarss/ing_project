package com.lswarss.ing_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val posts = mutableListOf<Post>()
        for(i in 0 .. 100){
            posts.add(Post(i,i,"Post #${i}", "Body #${i}"))
        }

        recyclerView.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PostsAdapter(posts)
        }

    }
}
