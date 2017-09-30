package com.webviander.hackathonapp.view

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.location.Location
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.akhgupta.easylocation.EasyLocationAppCompatActivity
import com.akhgupta.easylocation.EasyLocationRequestBuilder
import com.google.android.gms.location.LocationRequest
import com.pixplicity.easyprefs.library.Prefs
import com.webviander.hackathonapp.R
import com.webviander.hackathonapp.databinding.ActivityFeedBinding
import com.webviander.hackathonapp.model.FeedItem
import com.webviander.hackathonapp.util.PreferenceUtil
import com.webviander.hackathonapp.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.activity_feed.*
import java.util.*


class FeedActivity : EasyLocationAppCompatActivity(), Observer {

    lateinit var binding: ActivityFeedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayouts()
        setUpViewModel()
        setUpFeeds(binding.feedsList)
        binding.viewModel?.let { setUpObserver(it) }
        setUpLocation()
        setUpLocalLocation()
    }

    fun initLayouts() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feed)
    }

    fun setUpViewModel() {
        binding.viewModel = FeedViewModel(this)
    }

    fun setUpFeeds(recyclerView: RecyclerView) {
        var adapter = FeedsAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
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

    fun setUpLocalLocation() {
        val prefLat = Prefs.getString(PreferenceUtil.LATITUDE, null)
        val prefLng = Prefs.getString(PreferenceUtil.LONGITUDE, null)
        if (prefLat != null && prefLng != null) {
            //we have old location data. use it and display first.
            // later when location is got, we can refresh with new data
            loading_view.visibility = View.VISIBLE
            feeds_list.visibility = View.GONE
            binding.viewModel?.loadFeeds(prefLat.toDouble(), prefLng.toDouble())
        }
    }

    override fun update(p0: Observable?, p1: Any?) {
        Log.d("update", "called")
        if (p0 is FeedViewModel) {
            p1?.let {
                if (p1 == p0.backPressedTag) {
                    onBackPressed()
                } else if (p1 == p0.pendingLoadedTag) {
                    ongoing.background = null
                    pending.setBackgroundResource(R.drawable.border_rectangle)
                    onFeedsLoaded(p0.pendingList)
                } else if (p1 == p0.ongoingLoadedTag) {
                    pending.background = null
                    ongoing.setBackgroundResource(R.drawable.border_rectangle)
                    onFeedsLoaded(p0.ongoingList)
                } else if (p1 == p0.addFeedsClickedTag) {
                    onAddClicked()
                }
            }
        }
    }


    fun onFeedsLoaded(feedsList: ArrayList<FeedItem>) {

        if (feedsList.size > 0) {
            loading_view.visibility = View.GONE
            empty_view.visibility = View.GONE
            feeds_list.visibility = View.VISIBLE
            val adapter = binding.feedsList.adapter as FeedsAdapter
            adapter.feedsList = feedsList
        } else {
            //empty
            loading_view.visibility = View.GONE
            feeds_list.visibility = View.GONE
            empty_view.visibility = View.VISIBLE
        }
    }

    fun onAddClicked() {
        startActivity(AddFeedActivity.getIntent(this))
        finish()
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
        Log.d("onLocationReceived", "called $location")
        location?.let {
            //lat=12.8333&lng=80.0667
//            binding.viewModel?.loadFeeds(12.8333, 80.0667)
            Prefs.putString(PreferenceUtil.LATITUDE, location.latitude.toString())
            Prefs.putString(PreferenceUtil.LONGITUDE, location.longitude.toString())
            loading_view.visibility = View.VISIBLE
            binding.viewModel?.loadFeeds(location.latitude, location.longitude)
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, FeedActivity::class.java)
        }
    }
}
