package com.lswarss.ing_project.modules

import com.lswarss.ing_project.network.RetrofitInstance
import com.lswarss.ing_project.ui.PostsViewModel
import com.lswarss.ing_project.ui.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UserModule {
    val mainModule = module {
        single { RetrofitInstance() }
        viewModel { UserViewModel(user = get(), app = get()) }
    }
}