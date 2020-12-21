package com.staysilly.geotouristapp.views.ui;

import android.os.Bundle;
import android.util.Log;

import com.staysilly.geotouristapp.R;
import com.staysilly.geotouristapp.databinding.ActivityToursListBinding;
import com.staysilly.geotouristapp.models.Tour;
import com.staysilly.geotouristapp.viewmodels.ToursListViewModel;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class ToursListActivity extends BaseActivity {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = "**"+this.getClass().getSimpleName();
    private ActivityToursListBinding dataBinding;
    private ToursListViewModel viewModel;


    /*/////////////////////////////////////////////////
    //PRIVATE METHODS
    /*/////////////////////////////////////////////////
    private void initDataBinding(){
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_tours_list);
        viewModel = ViewModelProviders.of(this).get(ToursListViewModel.class);
        dataBinding.setViewModel(viewModel);
        dataBinding.setLifecycleOwner(this);
    }
    private void loadTours(){
        viewModel.getAllTours().observe(this, new Observer<List<Tour>>() {
            @Override
            public void onChanged(List<Tour> tours) {
                Log.d(TAG, "loadTours onChanged");
                if (tours==null || tours.isEmpty()){
                    return;
                }

                // TODO: 12/22/20  tools received
            }


        });
    }


    /*/////////////////////////////////////////////////
    //LIFECYCLE METHODS
    /*/////////////////////////////////////////////////
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        loadTours();
    }

}