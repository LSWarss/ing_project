package com.lswarss.ing_project.modules

import com.lswarss.ing_project.domain.PostItem
import com.lswarss.ing_project.domain.UserWithItem
import com.lswarss.ing_project.network.RetrofitInstance
import com.lswarss.ing_project.ui.CommentsViewModel
import com.lswarss.ing_project.ui.PhotosViewModel
import com.lswarss.ing_project.ui.PostsViewModel
import com.lswarss.ing_project.ui.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object CommentModule {
    val mainModule = module {
        single { RetrofitInstance() }
        viewModel {CommentsViewModel(post = get(), app = get()) }
    }
}