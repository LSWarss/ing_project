package com.lswarss.ing_project.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lswarss.ing_project.adapters.PostsAdapter
import com.lswarss.ing_project.databinding.FragmentPostsBinding
import com.lswarss.ing_project.ui.PostsViewModel


class PostsFragment : Fragment() {

    private val viewModel : PostsViewModel by lazy {
        ViewModelProvider(this).get(PostsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPostsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.recyclerViewPosts.apply{
            layoutManager = GridLayoutManager(activity,1)
            addOnScrollListener(this@PostsFragment.scrollListener)
        }


        binding.recyclerViewPosts.adapter = PostsAdapter(PostsAdapter.OnUserListener{
            viewModel.displayUserDetail(it)
        }, PostsAdapter.OnCommentsListener{
            viewModel.displayCommentsForPost(it)
        })


        viewModel.navigateToSelectedUser.observe(viewLifecycleOwner, Observer {
            if(null != it){
                this.findNavController().navigate(PostsFragmentDirections.navigationToUser(it))
                viewModel.displayUserDetailComplete()
            }
        })

        viewModel.navigateToSelectedComments.observe(viewLifecycleOwner, Observer {
            if(null != it){
                this.findNavController().navigate(PostsFragmentDirections.navigationToComments(it))
                viewModel.displayCommentsForPostComplete()
            }
        })

        return binding.root
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as GridLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount


            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThenVisible = totalItemCount >= 10
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThenVisible && isScrolling
            if(shouldPaginate){
                viewModel.postPagingLimit += 10
                viewModel.getPostsProperties()
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }
    }





}
