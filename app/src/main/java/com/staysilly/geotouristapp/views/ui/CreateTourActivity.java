package com.staysilly.geotouristapp.views.ui;

import android.os.Bundle;

import com.staysilly.geotouristapp.R;
import com.staysilly.geotouristapp.databinding.ActivityCreateTourBinding;
import com.staysilly.geotouristapp.viewmodels.CreateTourViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class CreateTourActivity extends BaseActivity {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = this.getClass().getSimpleName();
    private ActivityCreateTourBinding datBinding;
    private CreateTourViewModel viewModel;


    /*/////////////////////////////////////////////////
    //PRIVATE METHODS
    /*/////////////////////////////////////////////////
    private void initDataBinding(){
        datBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_tour);
        viewModel = ViewModelProviders.of(this).get(CreateTourViewModel.class);
        datBinding.setViewModel(viewModel);
        datBinding.setLifecycleOwner(this);
    }


    /*/////////////////////////////////////////////////
    //LIFECYCLE METHODS
    /*/////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
    }

}