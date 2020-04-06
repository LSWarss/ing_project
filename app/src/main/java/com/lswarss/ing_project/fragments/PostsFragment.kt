package com.lswarss.ing_project.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lswarss.ing_project.R
import com.lswarss.ing_project.adapters.PostsAdapter
import com.lswarss.ing_project.network.PostsApi
import com.lswarss.ing_project.repositories.PostsRepository
import kotlinx.android.synthetic.main.posts_fragment.*


class PostsFragment : Fragment() {

    private lateinit var viewModel: PostsViewModel
    private lateinit var factory : PostsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.posts_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = PostsApi()
        val repository = PostsRepository(api)
        factory = PostsViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(PostsViewModel::class.java)
        viewModel.getPosts()
        viewModel.posts.observe(viewLifecycleOwner, Observer {
            posts -> recycler_view_posts.also{
            it.layoutManager = LinearLayoutManager(requireContext())
            it.setHasFixedSize(true)
            it.adapter = PostsAdapter(posts)
        }
        })
    }
}
