package com.example.bookapp2.ui.setting;

import android.app.ProgressDialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookapp2.R;
import com.example.bookapp2.UserManagment;

import java.util.HashMap;

public class SettingViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    EditText Name, Phone, Firstname, Lastname;
    Button update, cPass;
    ProgressDialog progressDialog;
    UserManagment userManagment;
    TextView textFname;






    public SettingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is searcH fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }



}