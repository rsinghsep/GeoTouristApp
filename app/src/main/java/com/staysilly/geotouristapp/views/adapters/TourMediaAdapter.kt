package com.staysilly.geotouristapp.views.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.staysilly.geotouristapp.R
import com.staysilly.geotouristapp.databinding.MediaPreviewBinding

class TourMediaAdapter : RecyclerView.Adapter<TourMediaAdapter.TourMediaViewHolder>() {

    //members
    val TAG = "**TourMediaAdapter";
    var mediaPathList : ArrayList<String> = ArrayList()
    lateinit var dataBinding : MediaPreviewBinding

    //public methods
    fun setData(dataList : ArrayList<String>){
        if (dataList.isEmpty()){
            Log.d(TAG, "no media ref found")
            return
        }

        mediaPathList = dataList
        notifyDataSetChanged()
    }


    //lifecycle methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourMediaViewHolder {
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.media_preview, parent, false);
        return TourMediaViewHolder(dataBinding)
    }
    override fun getItemCount(): Int {
        return mediaPathList.size
    }
    override fun onBindViewHolder(holder: TourMediaViewHolder, position: Int) {
        var mediaRef = mediaPathList[position]
        Log.d(TAG, "media ref: "+ mediaRef)
    }


    //view holder class
    class TourMediaViewHolder(binding: MediaPreviewBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}