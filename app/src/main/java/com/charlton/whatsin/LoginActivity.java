package com.charlton.whatsin;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.annotations.NonNull;
import com.charlton.whatsin.data.models.Response;
import com.charlton.whatsin.data.models.UserModel;
import com.charlton.whatsin.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getName();
    ActivityLoginBinding binding;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setViewModel(this);


    }

    public View.OnClickListener onLoginClicked = view -> {
        if (validateForm()) {
            WhatsIn.getInstance().getYoService().login(binding.username.getText().toString(), binding.password.getText().toString())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(success -> {
                        if (success.getStatus() == 200) {
                            login(success);
                        } else {
                            Toast.makeText(LoginActivity.this, success.getExplain(), Toast.LENGTH_SHORT).show();
                        }
                    }, error -> {
                        Toast.makeText(LoginActivity.this,"An error occured", Toast.LENGTH_SHORT).show();
                    });
        }
    };

    private void login(Response<UserModel> success) {
        startActivity(MainActivity.create(this, success));
    }

    public View.OnClickListener onRegisterClicked = view -> {
        if (validateForm()) {
            WhatsIn.getInstance().getYoService().register(binding.username.getText().toString(), binding.password.getText().toString(), binding.username.getText().toString())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(success -> {
                        if (success.getStatus() == 200) {
                            login(success);
                        } else {
                            Toast.makeText(LoginActivity.this, success.getExplain(), Toast.LENGTH_SHORT).show();
                        }
                    }, error -> {
                        Toast.makeText(LoginActivity.this,"An error occured", Toast.LENGTH_SHORT).show();
                    });
        }
    };

    private boolean validateForm() {
        boolean valid = true;

        String email = binding.username.getText().toString();
        if (TextUtils.isEmpty(email)) {
            binding.username.setError("Required.");
            valid = false;
        } else {
            binding.username.setError(null);
        }

        String password = binding.password.getText().toString();
        if (TextUtils.isEmpty(password)) {
            binding.password.setError("Required.");
            valid = false;
        } else {
            binding.password.setError(null);
        }

        return valid;
    }


}
