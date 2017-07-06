package com.charlton.whatsin.adapters.recycler;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.charlton.whatsin.R;
import com.charlton.whatsin.adapters.recycler.holders.UPCHolder;
import com.charlton.whatsin.adapters.recycler.viewmodels.UPCViewModel;
import com.charlton.whatsin.data.models.UPC;
import com.charlton.whatsin.databinding.VhUpcBinding;

import java.util.ArrayList;

/**
 * Created by cj on 11/4/16.
 */

public class UPCRecyclerAdapter extends RecyclerView.Adapter<UPCHolder> {

    ArrayList<UPC> upcs = new ArrayList<>();

    @Override
    public UPCHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        VhUpcBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.vh_upc, parent, false);
        return new UPCHolder(binding);
    }

    @Override
    public void onBindViewHolder(UPCHolder holder, int position) {
        holder.binding.setViewModel(UPCViewModel.create(holder.binding.getRoot().getContext(), upcs.get(position)));
    }

    @Override
    public int getItemCount() {
        return upcs.size();
    }

    public void addUPC(UPC response) {
        upcs.add(response);
        notifyDataSetChanged();
    }
}
