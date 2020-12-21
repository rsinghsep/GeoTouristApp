package com.staysilly.geotouristapp.views.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.staysilly.geotouristapp.R;
import com.staysilly.geotouristapp.databinding.RowTourInfoBinding;
import com.staysilly.geotouristapp.models.Tour;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

class ToursAdapter extends RecyclerView.Adapter<ToursAdapter.ToursViewHolder>{

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = this.getClass().getSimpleName();
    private RowTourInfoBinding dataBinding;
    private List<Tour> tourList;


    /*/////////////////////////////////////////////////
    //PUBLIC METHODS
    /*/////////////////////////////////////////////////
    public void setData(List<Tour> tours){
        if (tours==null||tours.isEmpty()){
            Log.d(TAG, "no tours found");
            return;
        }

        tourList = tours;
    }
    public List<Tour> getTourList(){
        return tourList == null ? tourList = new ArrayList<>() : tourList;
    }


    /*/////////////////////////////////////////////////
    //LIFECYCLE METHODS
    /*/////////////////////////////////////////////////
    @NonNull
    @Override
    public ToursViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_tour_info, parent, false);
        tourList = new ArrayList<>();
        return new ToursViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ToursViewHolder holder, int position) {
        holder.setTour(getTourList().get(position));
    }

    @Override
    public int getItemCount() {
        return tourList == null ? 0 : tourList.size();
    }

    /*/////////////////////////////////////////////////
        //ViewHolder Class
        /*/////////////////////////////////////////////////
    class ToursViewHolder extends RecyclerView.ViewHolder {

        /*/////////////////////////////////////////////////
        //MEMBERS
        /*/////////////////////////////////////////////////
        private final String TAG = this.getClass().getSimpleName();
        private RowTourInfoBinding dataBinding;
        public void setTour(Tour tour){
            if (tour==null){
                return;
            }

            dataBinding.setTour(tour);
        }


        /*/////////////////////////////////////////////////
        //CONSTRUCTOR
        /*/////////////////////////////////////////////////
        public ToursViewHolder(@NonNull RowTourInfoBinding binding) {
            super(binding.getRoot());
            dataBinding = binding;
        }
    }

}
