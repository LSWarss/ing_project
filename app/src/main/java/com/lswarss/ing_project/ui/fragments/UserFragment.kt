package com.lswarss.ing_project.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.lswarss.ing_project.databinding.UserFragmentBinding
import com.lswarss.ing_project.domain.UserWithItem
import com.lswarss.ing_project.ui.PostsViewModel
import com.lswarss.ing_project.ui.UserViewModel
import com.lswarss.ing_project.ui.UserViewModelFactory
import kotlinx.android.synthetic.main.user_fragment.*

class UserFragment : Fragment(), OnMapReadyCallback{

    private lateinit var googleMap: GoogleMap
    private lateinit var userWithItem: UserWithItem

    private val viewModel : UserViewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = UserFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        userWithItem = UserFragmentArgs.fromBundle(arguments!!).postProperties

        val viewModelFactory = UserViewModelFactory(userWithItem, application)
        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)

        Log.d("latitude", "${userWithItem.user.address.geo.lat.toDouble()}")
        Log.d("longitude","${userWithItem.user.address.geo.lng.toDouble()}" )

        binding.photosBT.setOnClickListener{
            viewModel.displayUserDetail(userWithItem)
        }

        viewModel.navigateToSelectedUserPhotos.observe(viewLifecycleOwner, Observer {
            if(null != it){
                this.findNavController().navigate(UserFragmentDirections.actionUserFragmentToPhotosFragment(it))
                viewModel.displayUserDetailComplete()
            }
        })
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapView.onCreate(savedInstanceState)
        mapView.onResume()
        mapView.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let{
            googleMap = it
        }
        val user_geo = LatLng(userWithItem.user.address.geo.lat.toDouble(), userWithItem.user.address.geo.lng.toDouble())
        Log.d("latitude-onMapReady","${userWithItem.user.address.geo.lat.toDouble()}" )
        Log.d("longitude-onMapReady","${userWithItem.user.address.geo.lng.toDouble()}" )
        map?.addMarker(MarkerOptions().position(user_geo)
            .title("user_location"))
        map?.moveCamera(CameraUpdateFactory.newLatLng(user_geo))
        map?.moveCamera(CameraUpdateFactory.zoomTo(3F))
        map?.uiSettings?.isMyLocationButtonEnabled = false
        map?.uiSettings?.isZoomGesturesEnabled = false
        map?.uiSettings?.isScrollGesturesEnabled = false
        map?.uiSettings?.isTiltGesturesEnabled = false
    }

}