package com.example.giphyimagesapp_again_2025.models;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.giphyimagesapp_again_2025.MainActivity;
import com.example.giphyimagesapp_again_2025.R;
import com.example.giphyimagesapp_again_2025.serviceapi.ImageApiService;
import com.example.giphyimagesapp_again_2025.serviceapi.RetrofitInstance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageRepository {


    private ArrayList<ImageM> images = new ArrayList<>();
    private MutableLiveData<List<ImageM>> mutableLiveData = new MutableLiveData<>();

    private Application application;

    public ImageRepository(Application application) {
        this.application = application;
    }


    public MutableLiveData<List<ImageM>> getMutableLiveData(){

      //  Log.e("TE::::::::", "Esam Seit!");
        ImageApiService imageApiService = RetrofitInstance.getService();

        Call<Result> call = imageApiService
                .getTrendingGifImages(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                // Success
                Result result = response.body();
                if (result != null && result.getResults() != null){
                    images = (ArrayList<ImageM>) result.getResults();
                    mutableLiveData.setValue(images);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                // Error
              //  Log.e("TE::::::::", "Ir kluda!" + t);

            }
        });
        return mutableLiveData;

    }


    public MutableLiveData<List<ImageM>> getFilteredMutableLiveData(String str){
        ImageApiService imageApiService = RetrofitInstance.getService();
        Call<Result> call = imageApiService
                .getTrendingGifImages(application.getApplicationContext().getString(R.string.api_key));
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                // Success
                Result result = response.body();

                if (result != null && result.getResults() != null){

                    Iterator<ImageM> itr = result.getResults().iterator();
                    while(itr.hasNext()){
                        ImageM imgFilt = itr.next();
                        String imgStr = imgFilt.getTitle().toString().toLowerCase();
                        if (imgStr.contains(str) != true){
//                        if (imgFilt.getTitle().toLowerCase().equals(str.toLowerCase()) != true){
                            itr.remove();
                        } else {
//                            Toast.makeText(application.getApplicationContext(), imgStr + "!!!", Toast.LENGTH_SHORT).show();

                        }
                    }


                    images = (ArrayList<ImageM>) result.getResults();
                    mutableLiveData.setValue(images);
                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
//                // Error
//                Toast.makeText(application.getApplicationContext(), t + " | Error by getting API | " + call, Toast.LENGTH_SHORT).show();
//
//                Log.e(TAG,"Here is a problem: " + t);
            }
        });


        return mutableLiveData;
    }


    public MutableLiveData<List<ImageM>> getScrollingMutableLiveData(String str){
        ImageApiService imageApiService = RetrofitInstance.getService();
        Call<Result> call = imageApiService
                .getTrendingGifImages(application.getApplicationContext().getString(R.string.api_key));
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                // Success
                Result result = response.body();

                if (result != null && result.getResults() != null){

                    Iterator<ImageM> itr = result.getResults().iterator();
                    while(itr.hasNext()){
                        ImageM imgFilt = itr.next();
                        String imgStr = imgFilt.getTitle().toString().toLowerCase();
                        if (imgStr.contains(str) != true){
//                        if (imgFilt.getTitle().toLowerCase().equals(str.toLowerCase()) != true){
                            itr.remove();
                        } else {
//                            Toast.makeText(application.getApplicationContext(), imgStr + "!!!", Toast.LENGTH_SHORT).show();

                        }
                    }

                    images = (ArrayList<ImageM>) result.getResults();
                    mutableLiveData.setValue(images);
                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
//                // Error
//                Toast.makeText(application.getApplicationContext(), t + " | Error by getting API | " + call, Toast.LENGTH_SHORT).show();
//
//                Log.e(TAG,"Here is a problem: " + t);
            }
        });


        return mutableLiveData;
    }




}
