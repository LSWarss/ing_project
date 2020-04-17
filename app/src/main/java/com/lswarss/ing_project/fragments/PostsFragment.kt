package com.lswarss.ing_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lswarss.ing_project.adapters.PostsAdapter
import com.lswarss.ing_project.databinding.PostsFragmentBinding
import kotlinx.android.synthetic.main.post_fragment.view.*


class PostsFragment : Fragment(){

    private val viewModel : PostsViewModel by lazy {
        ViewModelProvider(this).get(PostsViewModel::class.java)
    }

    private val adapter = PostsAdapter(PostsAdapter.OnClickListener{

    })


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PostsFragmentBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

        viewModel.posts.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        binding.recyclerViewPosts.adapter = PostsAdapter(PostsAdapter.OnClickListener{
            viewModel.displayUser(it)
        })

        viewModel.navigateToSelectedUser.observe(viewLifecycleOwner, Observer {
            if(null != it){
                this.findNavController().navigate(PostsFragmentDirections.navigationToUser(it))
                viewModel.displayUserComplete()
            }
        })


        binding.recyclerViewPosts.adapter = adapter

        return binding.root
    }

}
