package com.lswarss.ing_project.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lswarss.ing_project.adapters.CommentsAdapter
import com.lswarss.ing_project.databinding.FragmentCommentsBinding
import com.lswarss.ing_project.ui.CommentsViewModel
import com.lswarss.ing_project.ui.CommentsViewModelFactory

class CommentsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentCommentsBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val userWithItem = CommentsFragmentArgs.fromBundle(requireArguments()).postProperties

        val viewModelFactory = CommentsViewModelFactory(userWithItem, application)
        binding.viewModel =
            ViewModelProvider(this, viewModelFactory).get(CommentsViewModel::class.java)

        binding.recyclerViewPosts.adapter = CommentsAdapter()

        return binding.root
    }

}