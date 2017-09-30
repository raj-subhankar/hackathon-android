package com.webviander.hackathonapp.view

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.akhgupta.easylocation.EasyLocationAppCompatActivity
import com.akhgupta.easylocation.EasyLocationRequestBuilder
import com.google.android.gms.location.LocationRequest
import com.pixplicity.easyprefs.library.Prefs
import com.webviander.hackathonapp.R
import com.webviander.hackathonapp.databinding.ActivityAddFeedBinding
import com.webviander.hackathonapp.util.PreferenceUtil
import com.webviander.hackathonapp.viewmodel.AddFeedViewModel
import java.util.*

class AddFeedActivity : EasyLocationAppCompatActivity(), Observer {


    lateinit var binding: ActivityAddFeedBinding
    var saveClicked = false
    var locationReceived = false
    var apiCalled = false
    var storedLocation: Location? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayouts()
        setUpViewModel()
        binding.viewModel?.let { setUpObserver(it) }
        setUpLocation()
    }

    fun initLayouts() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_feed)
    }

    fun setUpViewModel() {
        binding.viewModel = AddFeedViewModel(this)
    }

    fun setUpObserver(observable: Observable) {
        observable.addObserver(this)
    }

    fun setUpLocation() {
        val locationRequest = LocationRequest()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setInterval(5000)
                .setFastestInterval(5000)

        val easyLocationRequest = EasyLocationRequestBuilder()
                .setLocationRequest(locationRequest)
                .setFallBackToLastLocationTime(3000)
                .build()

        requestSingleLocationFix(easyLocationRequest)


    }

    override fun update(p0: Observable?, p1: Any?) {
        if (p0 is AddFeedViewModel) {
            p1?.let {
                if (p1 == p0.savePostTag) {
                    //save clicked now
                    saveClicked = true
                    if (locationReceived) {
                        //we already have location and now save is clicked. do api
                        binding.viewModel?.addPostApi(storedLocation?.latitude.toString(), storedLocation?.longitude.toString())
                    } else {
                        //save is clicked but location is not yet received. check in prefs
                        val prefLat = Prefs.getString(PreferenceUtil.LATITUDE, null)
                        val prefLng = Prefs.getString(PreferenceUtil.LONGITUDE, null)
                        if (prefLat != null && prefLng != null) {
                            //we have old location data. use it
                            binding.viewModel?.addPostApi(prefLat, prefLng)
                        } else {
                            //no location data at all!
                            Toast.makeText(this, "No location data available. Can't post at this moment!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onLocationProviderDisabled() {
        Log.d("onLocProviderDisabled", "called")

    }

    override fun onLocationPermissionGranted() {
        Log.d("onLocPermissionGranted", "called")

    }

    override fun onLocationProviderEnabled() {
        Log.d("onLocProviderEnabled", "called")

    }

    override fun onLocationPermissionDenied() {
        Log.d("onLocPermissionDenied", "called")

    }

    override fun onLocationReceived(location: Location?) {
        Log.d("onLocationReceived", "called")
        locationReceived = true
        location?.let {
            Prefs.putString(PreferenceUtil.LATITUDE, location.latitude.toString())
            Prefs.putString(PreferenceUtil.LONGITUDE, location.longitude.toString())
        }
    }

    override fun onBackPressed() {
        startActivity(FeedActivity.getIntent(this))
        finish()
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, AddFeedActivity::class.java)
        }
    }
}
