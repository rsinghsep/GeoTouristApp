package com.staysilly.geotouristapp.views.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.staysilly.geotouristapp.R;
import com.staysilly.geotouristapp.databinding.RowTourInfoBinding;
import com.staysilly.geotouristapp.models.Tour;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ToursAdapter extends RecyclerView.Adapter<ToursAdapter.ToursViewHolder>{

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = "**"+this.getClass().getSimpleName();
    private RowTourInfoBinding dataBinding;
    private List<Tour> tourList = new ArrayList<>();
    private OnCardClickListener onClickListener;


    /*/////////////////////////////////////////////////
    //PUBLIC METHODS
    /*/////////////////////////////////////////////////
    public void setData(List<Tour> tours){
        if (tours==null||tours.isEmpty()){
            Log.d(TAG, "no tours found");
            return;
        }

        tourList = tours;
        this.notifyDataSetChanged();
    }
    public void setClickListener(OnCardClickListener onClickListener){
        this.onClickListener = onClickListener;
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
        return new ToursViewHolder(dataBinding, this.onClickListener);
    }
    @Override
    public void onBindViewHolder(@NonNull ToursViewHolder holder, int position) {
        Log.d(TAG, "position : " + position);
       Tour tour = getTourList().get(position);
       Log.d(TAG, "tour name: " + tour.getTourName() + " start: " + tour.getStartAddress() + " end : " + tour.getDestinationAddress());
       holder.setTour(tour);
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
        public ToursViewHolder(@NonNull RowTourInfoBinding binding, final OnCardClickListener onCardClickListener) {
            super(binding.getRoot());
            dataBinding = binding;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Tour tour = tourList.get(getAdapterPosition());
                    onCardClickListener.onCardClicked(tour.getTourId());
                }
            });
        }
    }
    public interface OnCardClickListener{
        void onCardClicked(String tourId);
    }

}
