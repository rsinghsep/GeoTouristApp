package com.staysilly.geotouristapp.viewmodels;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private static final String TAG = "**BindingAdapters";


    /*/////////////////////////////////////////////////
    //PUBLIC METHODS
    /*/////////////////////////////////////////////////
    @BindingAdapter("mediaLocalRef")
    public static void setMediaPreviewFromLocal(ImageView imageView, String galleryRef){
        Log.d(TAG, "setMediaPreviewFromLocal");
        if (galleryRef == null){
            Log.d(TAG, "invalid galleryRef");
            return;
        }

        Log.d(TAG, "requesting to set the image from local gallery, ref: " + galleryRef);
        RequestBuilder requestBuilder = Glide.with(imageView.getContext()).load(galleryRef);
        List<RequestOptions> requestOptions = new ArrayList<>();
        requestOptions.add(RequestOptions.centerCropTransform());
        for (RequestOptions requestOption : requestOptions){
            requestBuilder.apply(requestOption);
        }
        requestBuilder.into(imageView);
    }
}
