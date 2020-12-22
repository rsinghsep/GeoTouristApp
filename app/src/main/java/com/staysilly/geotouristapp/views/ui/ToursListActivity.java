package com.staysilly.geotouristapp.views.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.staysilly.geotouristapp.R;
import com.staysilly.geotouristapp.databinding.ActivityToursListBinding;
import com.staysilly.geotouristapp.models.Tour;
import com.staysilly.geotouristapp.viewmodels.ToursListViewModel;
import com.staysilly.geotouristapp.views.adapters.ToursAdapter;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ToursListActivity extends BaseActivity {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = "**"+this.getClass().getSimpleName();
    private ActivityToursListBinding dataBinding;
    private ToursListViewModel viewModel;
    private ToursAdapter adapter;


    /*/////////////////////////////////////////////////
    //PRIVATE METHODS
    /*/////////////////////////////////////////////////
    private void initDataBinding(){
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_tours_list);
        viewModel = ViewModelProviders.of(this).get(ToursListViewModel.class);
        dataBinding.setViewModel(viewModel);
        dataBinding.setLifecycleOwner(this);
    }
    private ToursAdapter getAdapter(){
        return adapter == null ? adapter = new ToursAdapter() : adapter;
    }
    private void setUpRecyclerView(){
        RecyclerView recyclerView = dataBinding.toursList;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(getAdapter());
        getAdapter().setClickListener(new ToursAdapter.OnCardClickListener() {
            @Override
            public void onCardClicked(String tourId) {
                Log.d(TAG, "card clicked");
                if (tourId==null||tourId.isEmpty()){
                    Log.d(TAG, "invalid tour id");
                    return;
                }

                Log.d(TAG, "tour selected: " + tourId);
                Intent intent = new Intent(ToursListActivity.this, TourDetailActivity.class);
                intent.putExtra(TourDetailActivityKt.KEY_EXTRA_TOUR_ID, tourId);
                startActivity(intent);
            }
        });
    }
    private void loadTours(){
        viewModel.getAllTours().observe(this, new Observer<List<Tour>>() {
            @Override
            public void onChanged(List<Tour> tours) {
                Log.d(TAG, "loadTours onChanged");
                if (tours==null || tours.isEmpty()){
                    return;
                }

                getAdapter().setData(tours);
            }


        });
    }


    /*/////////////////////////////////////////////////
    //LIFECYCLE METHODS
    /*/////////////////////////////////////////////////
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setUpRecyclerView();
        loadTours();
    }

}