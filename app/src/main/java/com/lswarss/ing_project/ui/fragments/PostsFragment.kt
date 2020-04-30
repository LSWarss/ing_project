package com.lswarss.ing_project.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lswarss.ing_project.adapters.PostsAdapter
import com.lswarss.ing_project.databinding.PostsFragmentBinding
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
        val binding = PostsFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

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


}
