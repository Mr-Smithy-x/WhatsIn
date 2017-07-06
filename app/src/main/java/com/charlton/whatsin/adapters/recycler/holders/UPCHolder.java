package com.charlton.whatsin.adapters.recycler.holders;

import android.support.v7.widget.RecyclerView;

import com.charlton.whatsin.databinding.VhUpcBinding;

/**
 * Created by cj on 11/4/16.
 */
public class UPCHolder extends RecyclerView.ViewHolder {
    public VhUpcBinding binding;

    public UPCHolder(VhUpcBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
