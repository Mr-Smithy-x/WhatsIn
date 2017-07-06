package com.charlton.whatsin.adapters.recycler.viewmodels;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by cj on 11/4/16.
 */
public class BaseViewModel {

    protected Context context;

    protected BaseViewModel(Context context){
        this.context = context;
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).crossFade(750).into(imageView);
    }

}
