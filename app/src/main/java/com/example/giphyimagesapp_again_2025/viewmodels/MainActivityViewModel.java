package com.example.giphyimagesapp_again_2025.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.giphyimagesapp_again_2025.models.ImageM;
import com.example.giphyimagesapp_again_2025.models.ImageRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel{
    private ImageRepository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ImageRepository(application);
    }

    //Live Data
    public LiveData<List<ImageM>> getAllImages(){
        return repository.getMutableLiveData();
    }


    public LiveData<List<ImageM>> getFilteredImages (String str) {
        return repository.getFilteredMutableLiveData(str);
    }

    public LiveData<List<ImageM>> getScrolledImages (String str) {
        return repository.getScrollingMutableLiveData(str);
    }

}
