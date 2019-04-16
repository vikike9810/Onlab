package com.onlab.gymapp.Contact

import android.location.Geocoder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.OnMapReadyCallback

import com.onlab.gymapp.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_contacts.*
import java.util.*
import kotlin.collections.ArrayList

class ContactsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setLayout()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        var loc=getLocation(Gym.address)
        val addr = LatLng(loc[0], loc[1])
        mMap.addMarker(MarkerOptions().position(addr).title(Gym.name))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(addr))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15f),1000, null)
    }

    private fun setLayout() {
        ct_nev.setText(Gym.name)
        ct_tel.setText(Gym.phone)
        ct_cim.setText(Gym.address)
        ct_1.setText(Gym.monday)
        ct_2.setText(Gym.tuesday)
        ct_3.setText(Gym.wednesday)
        ct_4.setText(Gym.thursday)
        ct_5.setText(Gym.friday)
        ct_6.setText(Gym.saturday)
        ct_7.setText(Gym.sunday)
        ct_nyitva.setText("A mai napon " + getNyitvatartas()+"-ig van nyitva")
    }

    private fun getLocation(s :String):ArrayList<Double>{
        var geo=Geocoder(this,Locale.getDefault())
        var loc=geo.getFromLocationName(s,1)
        var ret=ArrayList<Double>(2)
        ret.add(loc[0].latitude)
        ret.add(loc[0].longitude)
        return  ret
    }

    private fun getNyitvatartas(): String {

        val d  = Date(Calendar.getInstance().timeInMillis).day
        var nyit: String=" nm jó"
        when(d){
            1->nyit=Gym.monday
            2->nyit=Gym.tuesday
            3->nyit=Gym.wednesday
            4->nyit=Gym.thursday
            5->nyit=Gym.friday
            6->nyit=Gym.saturday
            7->nyit=Gym.sunday
        }
        return nyit
    }
}
