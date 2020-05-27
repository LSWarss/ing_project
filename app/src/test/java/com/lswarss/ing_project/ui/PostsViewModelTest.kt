package com.lswarss.ing_project.ui

import android.app.Application
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lswarss.ing_project.MainCoroutineRule
import com.lswarss.ing_project.domain.UserWithItem
import com.lswarss.ing_project.getOrAwaitValue
import com.lswarss.ing_project.modules.PostModule
import com.lswarss.ing_project.network.PostsApiStatus
import com.lswarss.ing_project.observeForTesting
import com.lswarss.ing_project.repositories.PostsRepository
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.junit.Assert.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*

class PostsViewModelTest : KoinTest{


    val viewModel by inject<PostsViewModel>()
    val repository by inject<PostsRepository>()


    // Run tasks synchronously
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()


    /**
     * Taken from https://stackoverflow.com/questions/50184175/kotlin-coroutine-unit-testing-error-exception-in-thread-main-coroutine1-cor
     */
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var context : Application


    @Before
    fun before(){
        MockitoAnnotations.initMocks(this)
        startKoin{
            modules(PostModule.mainModule)
            androidContext(context)
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `data from api test`() {
        viewModel.getPostsProperties()
        viewModel.posts.observeForTesting {
            assertNotNull(viewModel.posts.getOrAwaitValue())
        }
    }

    @Test
    fun `data from db test`(){
        viewModel.getSavedPosts()
        viewModel.postsListFromDB?.observeForTesting {
            assertNotNull(viewModel.postsListFromDB?.getOrAwaitValue())
        }
    }

    @Test
    fun `data from search`(){
        viewModel.searchPosts(1)
        viewModel.searchedPosts.observeForTesting {
            assertNotNull(viewModel.searchedPosts.getOrAwaitValue())
        }
    }


}

