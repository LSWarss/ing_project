package com.lswarss.ing_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lswarss.ing_project.adapters.PostsAdapter
import com.lswarss.ing_project.databinding.PostsFragmentBinding


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

        binding.recyclerViewPosts.adapter = adapter

        setHasOptionsMenu(true)

        return binding.root
    }

}
