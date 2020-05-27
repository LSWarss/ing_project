package com.lswarss.ing_project.modules

import com.lswarss.ing_project.network.RetrofitInstance
import com.lswarss.ing_project.repositories.PostsRepository
import com.lswarss.ing_project.ui.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PostModule {
    val mainModule = module {
        single { RetrofitInstance()}
        single { PostsRepository(db = get())}
        viewModel { PostsViewModel(postsRepository = get())}
    }
}