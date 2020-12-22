package com.staysilly.geotouristapp.views.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.staysilly.geotouristapp.R
import com.staysilly.geotouristapp.databinding.ActivityTourDetailBinding
import com.staysilly.geotouristapp.viewmodels.TourDetailViewModel

class TourDetailActivity : AppCompatActivity() {

    //members
    val TAG = localClassName;
    private lateinit var dataBinding : ActivityTourDetailBinding;
    private lateinit var viewModel : TourDetailViewModel;


    //private
    fun initDataBinding(){
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_tour_detail);
        viewModel = ViewModelProviders.of(this).get(TourDetailViewModel::class.java);
        dataBinding.setLifecycleOwner(this);
    }

    //lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tour_detail)
    }

}