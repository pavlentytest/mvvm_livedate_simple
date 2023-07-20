package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setViewModel(loginViewModel);
        binding.setLifecycleOwner(this);
        loginViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User u) {
                User user = u;
                if(user != null) {
                    if(user.getEmail().length() > 0 || user.getPassword().length() > 0) {
                        Snackbar.make(findViewById(R.id.container),
                                "Email: "+user.getEmail() +", password: "+user.getPassword(),
                                Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}