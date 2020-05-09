package com.lswarss.ing_project.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lswarss.ing_project.adapters.PhotosAdapter
import com.lswarss.ing_project.databinding.FragmentUserPhotosBinding
import com.lswarss.ing_project.ui.PhotosViewModel
import com.lswarss.ing_project.ui.PhotosViewModelFactory

class PhotosFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentUserPhotosBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val userWithItem = PhotosFragmentArgs.fromBundle(requireArguments()).postProperties

        val viewModelFactory = PhotosViewModelFactory(userWithItem, application)
        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(PhotosViewModel::class.java)

        binding.recyclerViewPosts.adapter = PhotosAdapter()
        return binding.root
    }


}