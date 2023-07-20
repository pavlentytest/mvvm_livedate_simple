package com.example.myapplication;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> emailError = new MutableLiveData<>();
    public MutableLiveData<String> passwordError = new MutableLiveData<>();

    private MutableLiveData<User> userMutableLiveData;

    public LoginViewModel() {

    }

    LiveData<User> getUser() {
        if(userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    public void onSignInClicked() {
        emailError.setValue(null);
        passwordError.setValue(null);
        User user = new User(email.getValue(), password.getValue());

        if(email.getValue() == null || email.getValue().isEmpty()) {
            emailError.setValue("Please enter email");
            return;
        }
        if(!user.isEmailValid()) {
            emailError.setValue("Please enter a valid email");
            return;
        }
        if(password.getValue() == null || password.getValue().isEmpty()) {
            passwordError.setValue("Please enter password");
            return;
        }
        if(!user.checkLength()) {
            passwordError.setValue("Password length should be greater than 6");
            return;
        }
        userMutableLiveData.setValue(user);
    }

}
