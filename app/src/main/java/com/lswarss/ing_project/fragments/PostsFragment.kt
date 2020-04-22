package com.lswarss.ing_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.lswarss.ing_project.CommentsFragmentArgs
import com.lswarss.ing_project.R
import com.lswarss.ing_project.adapters.PostsAdapter
import com.lswarss.ing_project.databinding.PostsFragmentBinding
import com.lswarss.ing_project.domain.UserItem
import com.lswarss.ing_project.domain.UserWithItem
import kotlinx.android.synthetic.main.post_fragment.view.*



class PostsFragment : Fragment(), View.OnClickListener {

    private val viewModel : PostsViewModel by lazy {
        ViewModelProvider(this).get(PostsViewModel::class.java)
    }

    lateinit var navController: NavController

    private val adapter = PostsAdapter()

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<TextView>(R.id.post_comment).setOnClickListener(this)
        view.findViewById<TextView>(R.id.post_user).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.post_user -> {
                val bundle = bundleOf("postProperties" to UserFragmentArgs)
                navController!!.navigate(R.id.navigation_to_user)
            }
            R.id.post_comment -> {
                val bundle = bundleOf("postProperties" to CommentsFragmentArgs)
                navController!!.navigate(R.id.navigation_to_comments)
            }
        }
    }
}
