package com.example.sandy.kotlin_google_places

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var gMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var frag: SupportMapFragment =supportFragmentManager.
                findFragmentById(R.id.frag) as SupportMapFragment

        frag.getMapAsync {

            gMap = it
        }

        btn.setOnClickListener {
            val builder = PlacePicker.IntentBuilder()
            startActivityForResult(builder.build(this@MainActivity),
                    1)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val place = PlacePicker.getPlace(data!!, this)
        tv1.text = place.latLng.latitude.toString()
        tv2.text = place.latLng.longitude.toString()
        tv3.text = place.name.toString()

        var options = MarkerOptions( )
        options.position(LatLng(place.latLng.latitude,
                place.latLng.longitude))
        options.title(place.name.toString())
        gMap!!.addMarker(options)
        gMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(
                LatLng(place.latLng.latitude,
                        place.latLng.longitude),15f))

    }
}
