package com.lswarss.ing_project.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.lswarss.ing_project.MainActivity
import com.lswarss.ing_project.adapters.PostsAdapter
import com.lswarss.ing_project.databinding.FragmentPostsBinding
import com.lswarss.ing_project.db.PostsDatabase
import com.lswarss.ing_project.repositories.PostsRepository
import com.lswarss.ing_project.ui.PostsViewModel
import com.lswarss.ing_project.ui.PostsViewModelProviderFactory

class SavedPostsFragment : Fragment() {

    private val viewModel : PostsViewModel by lazy {
        ViewModelProvider(this).get(PostsViewModel::class.java)
    }

    lateinit var savedPostsAdapter : PostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPostsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        var db : PostsDatabase = (activity as MainActivity).postsDatabase!!

        var repository = PostsRepository(db)
        val viewModelFactory = PostsViewModelProviderFactory(repository)
        binding.viewModel =  ViewModelProvider(this, viewModelFactory).get(PostsViewModel::class.java)

        viewModel.getSavedPosts()

        binding.recyclerViewPosts.apply{
            layoutManager = GridLayoutManager(activity,1)
            adapter = PostsAdapter(PostsAdapter.OnUserListener{
//                viewModel.displayUserDetail(it)
            }, PostsAdapter.OnCommentsListener{
                viewModel.displayCommentsForPost(it)
            }, PostsAdapter.OnSaveListener{
                viewModel.deletePost(it)
            })

            //We initialize savedPostsAdapter with adapter to then use it for submiting a list
            savedPostsAdapter = adapter as PostsAdapter
        }

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

        viewModel.postsListFromDB?.observe(viewLifecycleOwner, Observer {
            savedPostsAdapter.submitList(it)
        })



        return binding.root
    }




}