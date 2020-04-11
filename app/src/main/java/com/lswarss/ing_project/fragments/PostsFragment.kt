package com.lswarss.ing_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.lswarss.ing_project.R
import com.lswarss.ing_project.adapters.PostsAdapter
import com.lswarss.ing_project.databinding.PostsFragmentBinding
import kotlinx.android.synthetic.main.posts_fragment.*


class PostsFragment : Fragment(){

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

        binding.recyclerViewPosts.adapter = PostsAdapter(PostsAdapter.OnClickListener{

        })

        setHasOptionsMenu(true)

        return binding.root


    }

}
