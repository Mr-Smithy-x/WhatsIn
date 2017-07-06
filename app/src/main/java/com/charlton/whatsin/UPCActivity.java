package com.charlton.whatsin;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.charlton.whatsin.data.models.UPC;
import com.charlton.whatsin.databinding.ActivityUpcBinding;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class UPCActivity extends AppCompatActivity {

    private static final String UPC_KEY = "UPC";

    private UPC upc;
    private ActivityUpcBinding binding;
    public View.OnClickListener onShareClicked = view -> {
        shareTextUrl("https://yoprice.co/upc?id=" + upc.getCode());
    };



    public String getAllergens() {
        return upc.getProduct().getAllergens();
    }

    public String getProduct() {
        return upc.getProduct().getProduct_name();
    }

    public String getProductImage() {
        return upc.getProduct().getImage_front_url();
    }

    public String getIngred() {
        return upc.getProduct().getIngredients_text();
    }

    public String getServingSize(){
        return upc.getProduct().getServing_size();
    }

    private void shareTextUrl(String text) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        if (data != null) {
            String id = data.getPathSegments().get(data.getPathSegments().size());
            Log.e("ID", id);
            WhatsIn.getWhatsInService().getUPC(id).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(success -> {
                        if (success.getStatus() != 0) {
                            UPCActivity.this.upc = success;
                            init();
                        }
                    }, error -> {

                    });
        } else {
            upc = (UPC) getIntent().getSerializableExtra(UPC_KEY);
            init();
        }

    }


    public void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_upc);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        binding.setViewModel(this);
        binding.contentUpc.setViewModel(this);
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).crossFade(750).into(imageView);
    }

    public static Intent create(Context context, UPC upc) {
        Intent intent = new Intent(context, UPCActivity.class);
        intent.putExtra(UPC_KEY, upc);
        return intent;
    }
}
