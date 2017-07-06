package com.charlton.whatsin.adapters.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.charlton.whatsin.adapters.recycler.holders.HomeHolder;

import java.util.ArrayList;

/**
 * Created by cj on 11/4/16.
 */
public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeHolder> {

    ArrayList<?> arrayList = new ArrayList<>();

    @Override
    public HomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(HomeHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
