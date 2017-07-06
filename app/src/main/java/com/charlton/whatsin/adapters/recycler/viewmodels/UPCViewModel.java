package com.charlton.whatsin.adapters.recycler.viewmodels;

import android.content.Context;
import android.view.View;

import com.charlton.whatsin.UPCActivity;
import com.charlton.whatsin.data.models.UPC;

/**
 * Created by cj on 11/4/16.
 */

public class UPCViewModel extends BaseViewModel {

    private UPC upc;

    public String getTitle() {
        return getUpc().getProduct().getProduct_name();
    }

    public String getCategory() {
        return "Category: " + getUpc().getProduct().getCategories();
    }

    public String getImage() {
        return getUpc().getProduct().getImage_front_url();
    }

    public UPC getUpc() {
        return upc;
    }


    protected UPCViewModel(Context context, UPC upc) {
        super(context);
        this.upc = upc;
    }

    public static UPCViewModel create(Context context, UPC upc) {
        return new UPCViewModel(context, upc);
    }

    public View.OnClickListener OnUPCClicked = view -> {
        context.startActivity(UPCActivity.create(context, upc));
    };

}
