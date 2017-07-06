package com.charlton.whatsin;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.charlton.whatsin.adapters.viewpager.HomePagerAdapter;
import com.charlton.whatsin.data.models.Response;
import com.charlton.whatsin.data.models.UserModel;
import com.charlton.whatsin.databinding.ActivityMainBinding;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private static final String USER_KEY = "USER_KEY";
    ActivityMainBinding binding = null;
    private HomePagerAdapter homePager;
    ActionBarDrawerToggle toggle;
    private UserModel userModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        if (getIntent() != null) {
            userModel = (UserModel) getIntent().getSerializableExtra(USER_KEY);

            ((AppCompatTextView) binding.navDrawer.getHeaderView(0).findViewById(R.id.user_name)).setText(userModel.getUser_name());
            Glide.with(this).load(userModel.getUser_profile_avatar()).crossFade(750).into((CircleImageView) binding.navDrawer.getHeaderView(0).findViewById(R.id.user_profile));
            ((AppCompatTextView) binding.navDrawer.getHeaderView(0).findViewById(R.id.user_email)).setText(userModel.getUser_email());
        }
        setSupportActionBar(binding.toolbar);
        toggle = new ActionBarDrawerToggle(this, binding.drawer, binding.toolbar, R.string.app_name, R.string.app_name);
        binding.drawer.addDrawerListener(toggle);
        toggle.syncState();
        binding.viewPager.setAdapter(homePager = new HomePagerAdapter(getSupportFragmentManager()));
        binding.tablayout.setupWithViewPager(binding.viewPager);
        binding.setViewModel(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                binding.viewPager.setCurrentItem(1,true);
                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                homePager.searchUPC(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public View.OnClickListener OnScannerClicked = view -> {
        new IntentIntegrator(this).initiateScan();
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public static Intent create(Context context, Response<UserModel> success) {
        Intent i = new Intent(context, MainActivity.class);
        i.putExtra(USER_KEY, success.getData());
        return i;
    }
}
