package com.lswarss.ing_project.ui.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lswarss.ing_project.R
import com.lswarss.ing_project.adapters.PhotosAdapter
import com.lswarss.ing_project.databinding.FragmentUserPhotosBinding
import com.lswarss.ing_project.ui.PhotosViewModel
import com.lswarss.ing_project.ui.PhotosViewModelFactory
import kotlin.random.Random

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

        binding.recyclerViewPosts.adapter = PhotosAdapter(PhotosAdapter.OnClickListener{
            val random =  Random.nextInt(0,3)
            val mediaPlayer = SoundPicker(random)
            mediaPlayer.isLooping
            mediaPlayer.start()
        })

        return binding.root
    }


    /**
     * My own function to make photo displaying a little bit more appealing, the algorithm is simple
     * but it will be upgraded and made even better
     * # Files with wav extensions are shown as an error but it's totally fine, android studio have
     * some problems with them
     */
    private fun SoundPicker(random : Int) : MediaPlayer
    {
        if(random == 0){
           return MediaPlayer.create(context, R.raw.kick)
        }
        else if(random == 1){
            return MediaPlayer.create(context, R.raw.clap)
        }
        else if(random == 2) {
            return MediaPlayer.create(context, R.raw.ht)
        }
        else{
            return MediaPlayer.create(context, R.raw.bongo)
        }
    }


}