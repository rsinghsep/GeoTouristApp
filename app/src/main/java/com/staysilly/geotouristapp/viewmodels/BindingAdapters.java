package com.staysilly.geotouristapp.viewmodels;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

class BindingAdapters {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    private final String TAG = this.getClass().getSimpleName();


    /*/////////////////////////////////////////////////
    //PUBLIC METHODS
    /*/////////////////////////////////////////////////
    @BindingAdapter("mediaLocalRef")
    public static void setMediaPreviewFromLocal(ImageView imageView, String galleryRef){
        if (galleryRef == null){
            return;
        }

        // TODO: 12/22/20
    }
}
