package com.example.bookapp2.ui.grades;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GradesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GradesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is grades fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}