package com.lswarss.ing_project.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lswarss.ing_project.databinding.UserFragmentBinding
import com.lswarss.ing_project.ui.UserViewModel
import com.lswarss.ing_project.ui.UserViewModelFactory

class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = UserFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        val userWithItem = UserFragmentArgs.fromBundle(arguments!!).postProperties
        val viewModelFactory = UserViewModelFactory(
            userWithItem,
            application
        )
        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)
        return binding.root
    }
}