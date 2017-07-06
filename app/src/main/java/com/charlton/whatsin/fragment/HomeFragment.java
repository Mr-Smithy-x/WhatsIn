package com.charlton.whatsin.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.charlton.whatsin.R;
import com.charlton.whatsin.WhatsIn;
import com.charlton.whatsin.adapters.recycler.HomeRecyclerAdapter;
import com.charlton.whatsin.adapters.recycler.UPCRecyclerAdapter;
import com.charlton.whatsin.databinding.HomeFragmentBinding;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cj on 11/4/16.
 */

public class HomeFragment extends BaseFragment {

    private static final String TYPE = "TYPE";
    public static final int HOME = 0;
    public static final int RESULT = 1;
    @TYPE
    public int type;
    HomeFragmentBinding binding;
    private HomeRecyclerAdapter homeAdapter;
    private UPCRecyclerAdapter upcRecyclerAdapter;

    @Override
    public void Search(String query) {
        if (type == RESULT) {
            WhatsIn.getWhatsInService().getUPC(query)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        if(response.getStatus() != 0) {
                            upcRecyclerAdapter.addUPC(response);
                        }else{
                            Toast.makeText(getActivity(), "We couldn't find that UPC code!", Toast.LENGTH_SHORT).show();
                        }
                    }, error -> {
                        Log.e("ERROR", "UNABLE TO GET CONTENT", error);
                    });
        }
    }

    @IntDef({HOME, RESULT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switch (getArguments().getInt(TYPE,RESULT)) {
            case HOME:
                type = HOME;
                binding.recycler.setAdapter(homeAdapter = new HomeRecyclerAdapter());
                binding.recycler.setVisibility(View.GONE);
                binding.layout.setVisibility(View.VISIBLE);
                break;
            case RESULT:
                type = RESULT;
                binding.recycler.setAdapter(upcRecyclerAdapter = new UPCRecyclerAdapter());
                binding.recycler.setVisibility(View.VISIBLE);
                binding.layout.setVisibility(View.GONE);

                break;
        }
    }


    public static HomeFragment create(@TYPE int type) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle b = new Bundle();
        b.putInt(TYPE, type);
        homeFragment.setArguments(b);
        return homeFragment;
    }
}
