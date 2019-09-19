package com.kuldeep.deliverylist.detail

import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kuldeep.deliverylist.R
import com.kuldeep.posts.core.application.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_item.*
import kotlin.properties.Delegates

class DetailActivity : BaseActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var lat by Delegates.notNull<Double>()
    private var lng by Delegates.notNull<Double>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_detail_activity)
        val imageurl=intent.getStringExtra("imageUrl")
        val description=intent.getStringExtra("description")
        val latitude=intent.getStringExtra("lat")
        val lngitude=intent.getStringExtra("lng")
        val address=intent.getStringExtra("address")
        lat=   latitude.toDouble()
        lng=lngitude.toDouble()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        Picasso.with(applicationContext).load(imageurl).into(ivAvatar)
        tvTitle.text=description
        tvBody.text=address

    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(lat,lng)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}