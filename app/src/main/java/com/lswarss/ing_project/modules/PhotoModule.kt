package com.lswarss.ing_project.modules

import com.lswarss.ing_project.network.RetrofitInstance
import com.lswarss.ing_project.ui.PhotosViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PhotoModule {
    val mainModule = module {
        single { RetrofitInstance() }
        viewModel { PhotosViewModel(user = get(), app = get()) }
    }
}