package com.onlab.gymapp.Contact

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.OnMapReadyCallback

import com.onlab.gymapp.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_contacts.*
import java.util.*

class ContactsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        //setLayout()
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

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
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
