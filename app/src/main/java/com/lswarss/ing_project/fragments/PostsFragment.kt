package com.lswarss.ing_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lswarss.ing_project.R
import com.lswarss.ing_project.adapters.PostsAdapter
import com.lswarss.ing_project.domain.PostItem
import com.lswarss.ing_project.network.PostsApi
import com.lswarss.ing_project.repositories.PostsRepository
import com.lswarss.ing_project.util.RecyclerViewClickListener
import kotlinx.android.synthetic.main.posts_fragment.*


class PostsFragment : Fragment(), RecyclerViewClickListener{

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
        // changed the viewmodelproviders.of() to new ViewModelProvider
        viewModel = ViewModelProvider(this, factory).get(PostsViewModel::class.java)
        viewModel.getPosts()
        viewModel.posts.observe(viewLifecycleOwner, Observer {
            posts -> recycler_view_posts.also{
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = PostsAdapter(posts, this)
        }
        })
    }

    override fun onRecyclerViewItemClicked(view: View, post: PostItem) {
        when(view.id){
            R.id.post_user -> {
                Toast.makeText(requireContext(), "User Clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.post_comment -> {
                Toast.makeText(requireContext(), "Comments Clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
