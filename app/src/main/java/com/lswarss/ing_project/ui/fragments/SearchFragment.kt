package com.lswarss.ing_project.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.lswarss.ing_project.MainActivity
import com.lswarss.ing_project.R
import com.lswarss.ing_project.adapters.PostsAdapter
import com.lswarss.ing_project.databinding.FragmentSearchBinding
import com.lswarss.ing_project.db.PostsDatabase
import com.lswarss.ing_project.repositories.PostsRepository
import com.lswarss.ing_project.ui.PostsViewModel
import com.lswarss.ing_project.ui.PostsViewModelProviderFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: PostsViewModel by lazy {
        ViewModelProvider(this).get(PostsViewModel::class.java)
    }

    lateinit var searchedPostsAdapter: PostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchBinding.inflate(inflater)
        binding.lifecycleOwner = this
        var db: PostsDatabase = (activity as MainActivity).postsDatabase!!

        var repository = PostsRepository(db)
        val viewModelFactory = PostsViewModelProviderFactory(repository)
        binding.viewModel =
            ViewModelProvider(this, viewModelFactory).get(PostsViewModel::class.java)


        binding.recyclerViewPosts.apply {
            adapter = PostsAdapter(PostsAdapter.OnUserListener {
                viewModel.displayUserDetail(it)
            }, PostsAdapter.OnCommentsListener {
                viewModel.displayCommentsForPost(it)
            }, PostsAdapter.OnSaveListener {
                viewModel.savePosts(it)
            })
            layoutManager = GridLayoutManager(activity, 1)

            searchedPostsAdapter = adapter as PostsAdapter
        }


        var job: Job? = null
        binding.etSearch?.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L) //TODO: Add class for globals
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchPosts(editable.toString().toInt())
                    }
                }
            }
        }

        viewModel.navigateToSelectedUser.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(PostsFragmentDirections.navigationToUser(it))
                viewModel.displayUserDetailComplete()
            }
        })

        viewModel.navigateToSelectedComments.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(PostsFragmentDirections.navigationToComments(it))
                viewModel.displayCommentsForPostComplete()
            }
        })

        viewModel.searchedPosts.observe(viewLifecycleOwner, Observer {
            searchedPostsAdapter.submitList(it)
        })


        return binding.root
    }

}