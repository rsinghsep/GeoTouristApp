package com.staysilly.geotouristapp.views.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.staysilly.geotouristapp.R
import com.staysilly.geotouristapp.databinding.ActivityTourDetailBinding
import com.staysilly.geotouristapp.viewmodels.TourDetailViewModel
import com.staysilly.geotouristapp.views.adapters.TourMediaAdapter
import java.lang.StringBuilder

public const val KEY_EXTRA_TOUR_ID = "TourDetailActivity_KEY_EXTRA_TOUR_ID"
public class TourDetailActivity : AppCompatActivity() {

    //members
    val TAG = "**TourDetailActivity"
    private lateinit var dataBinding : ActivityTourDetailBinding
    private lateinit var viewModel : TourDetailViewModel
    val tourMediaAdapter = TourMediaAdapter()


    //private
    fun initDataBinding(){
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_tour_detail)
        viewModel = ViewModelProviders.of(this).get(TourDetailViewModel::class.java)
        dataBinding.setLifecycleOwner(this)
    }
    fun readBundle(bundle: Bundle){
        val key : String =  bundle.getString(KEY_EXTRA_TOUR_ID, "")
        if (key.isEmpty()){
            Log.d(TAG, "KEY_EXTRA_TOUR_ID -> invalid")
            return;
        }
        Log.d(TAG, "tour Id: " + key)
        observerTourDetail(key)
    }
    fun observerTourDetail(tourId : String){
        viewModel.getTourById(tourId).observe(this, Observer {
            Log.d(TAG, "tour received")

            dataBinding.tour = it
            val mediaRefList = it.tourMediaPathList
            if (mediaRefList==null||mediaRefList.isEmpty()){
                Log.d(TAG, "no media found")
                return@Observer
            }
            Log.d(TAG, "total media: " + mediaRefList.size)
            tourMediaAdapter.setData(it.tourMediaPathList)
        })
    }
    fun setupMediaRecyclerView(){
        val recyclerView : RecyclerView = dataBinding.mediaList
        recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.adapter = tourMediaAdapter
    }

    //lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        intent.extras?.let { readBundle(it) }
        setupMediaRecyclerView()
    }

}