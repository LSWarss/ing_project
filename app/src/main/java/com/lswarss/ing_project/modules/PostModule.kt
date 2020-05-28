package com.lswarss.ing_project.modules

import androidx.room.Room
import com.lswarss.ing_project.db.PostsDatabase
import com.lswarss.ing_project.network.RetrofitInstance
import com.lswarss.ing_project.repositories.PostsRepository
import com.lswarss.ing_project.ui.PostsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PostModule {
    val mainModule = module {
        single { RetrofitInstance()}
        single {
            Room.databaseBuilder(androidContext(), PostsDatabase::class.java, "posts_db").build()
        }
        single { PostsRepository(db = get())}
        viewModel { PostsViewModel(postsRepository = get())}
    }
}