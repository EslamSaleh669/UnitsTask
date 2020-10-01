package com.eslam.task.View

import android.os.Bundle
import android.util.Log
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.eslam.task.Adapter.AttachmentsAdapter
import com.eslam.task.Model.UnitModel.Data
import com.eslam.task.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_unit_details.*
import kotlinx.android.synthetic.main.layout_list_details.*


class UnitDetails : AppCompatActivity(), OnMapReadyCallback {

    private var txtRoom: TextView? = null
    private var txtGuests: TextView? = null
    private var txtBeds: TextView? = null
    private var txtBathrooms: TextView? = null
    private var txtBalacons: TextView? = null
    private var txtPrice: TextView? = null
    private var txtCurrency: TextView? = null
    private var txtTitle: TextView? = null
    private var txtDescription: TextView? = null
    private var txtHostName: TextView? = null
    private var txtCheckIn: TextView? = null
    private var txtCheckOut: TextView? = null
    private var txtPriceValue: TextView? = null
    private var txtCurrencyValue: TextView? = null

    private var txtRate: RatingBar? = null
    private var txtRateBottom: RatingBar? = null
    private var txtRateCount: TextView? = null
    private var txtRateCountBottom: TextView? = null

    private var data: Data? = null
    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unit_details)

        initView()
    }

    private fun initView() {
        txtRoom = this.numberRooms
        txtGuests = this.numberGuest
        txtBeds = this.numberBeds
        txtBathrooms = this.numberBathroom
        txtBalacons = this.numberBalacons
        txtPrice = this.price
        txtCurrency = this.textCurrency
        txtTitle = this.titleUnit
        txtDescription = this.description
        txtHostName = this.hostName
        txtCheckIn = this.checkIn
        txtCheckOut = this.checkOut
        txtCurrencyValue = this.currencyValue
        txtPriceValue = this.priceValue
        txtRateCount = this.rateCount
        txtRateCountBottom = this.rateCountBottom
        txtRate = this.rating
        txtRateBottom = this.ratingBottom

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setData()
    }

    private fun setData() {
        data = intent.getSerializableExtra("data") as? Data

        txtRoom!!.text = data!!.rooms.toString()
        txtBeds!!.text = data!!.beds.toString()
        txtGuests!!.text = data!!.guests.toString()
        txtBathrooms!!.text = data!!.bathrooms.toString()
        txtBalacons!!.text = data!!.balacons.toString()

        txtTitle!!.text = data!!.title
        txtCurrency!!.text = data!!.currency
        txtDescription!!.text = data!!.description
        txtPrice!!.text = data!!.price.toString()
        txtHostName!!.text = "Mohamed abdelfatah mohamed selim"

        txtCheckIn!!.text = data!!.checkin
        txtCheckOut!!.text = data!!.checkout
        txtCurrencyValue!!.text = data!!.currency
        txtPriceValue!!.text = data!!.price.toString()

        txtRateCount!!.text = data!!.rate_count.toString()
        txtRateCountBottom!!.text = data!!.rate_count.toString()

        txtRate!!.rating = data!!.rate_count.toFloat()
        txtRateBottom!!.rating = data!!.rate_count.toFloat()


        setAttachmentSlider();

    }

    private fun setAttachmentSlider() {
        val adapter = AttachmentsAdapter(data!!.attachments)
        photos_viewpager.adapter = adapter

        TabLayoutMediator(tab_layout, photos_viewpager) { tab, position ->
        }.attach()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        if (googleMap != null) {
            mMap = googleMap
        }

        val location = LatLng(data!!.longitude, data!!.latitude)
        mMap.addMarker(MarkerOptions().position(location).title(data!!.title))
        val currentZoom = mMap.cameraPosition.zoom

        Log.e("** c",currentZoom.toString())


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15f));
        googleMap!!.animateCamera(CameraUpdateFactory.zoomIn());
        googleMap!!.animateCamera(CameraUpdateFactory.zoomTo(15f), 2000, null);
    }
}