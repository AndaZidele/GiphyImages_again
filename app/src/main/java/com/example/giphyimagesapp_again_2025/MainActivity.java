package com.example.giphyimagesapp_again_2025;

import android.os.Bundle;
import android.os.Handler;
import android.widget.AbsListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giphyimagesapp_again_2025.adapters.ImageMAdapter;
import com.example.giphyimagesapp_again_2025.databinding.ActivityMainBinding;
import com.example.giphyimagesapp_again_2025.models.ImageM;
import com.example.giphyimagesapp_again_2025.viewmodels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ArrayList<ImageM> images;
    private RecyclerView recyclerView;
    private ImageMAdapter imageAdapter;
    private MainActivityViewModel viewModel;

    private ConstraintLayout constraintLayout;
    private ActivityMainBinding binding;

    private SearchView searchView;

    private int currentPage = 1; // Start with page 1
    private boolean isLoading = false; // Prevent multiple loads at the same time
    private int totalPages = 10; // Set this based on the total available pages (you can adjust this logic)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_main
        );

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);


//        recyclerView = binding.mainRecyclerView;
//        imageAdapter = new ImageMAdapter(this, images);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(imageAdapter);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        getTrendingImages();

        //    constraintLayout = binding.main;

        searchView = findViewById(R.id.mainSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                scrollingFunc(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 100);
                return true;
            }
        });

/*
        //recyclerView = findViewById(R.id.mainRecyclerView);
       //  recyclerView.setLayoutManager(new LinearLayoutManager(this));
      //  imageAdapter = new ImageMAdapter(MainActivity.this, images);
       // recyclerView.setAdapter(imageAdapter);
//        if (recyclerView != null) {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

//                if (!binding.mainRecyclerView.canScrollVertically(1)) {
////                    if (currentPage <= totalAvailablePages) {
////                        currentPage +=1;
                        Toast.makeText(getApplicationContext(), "To scrolling function!", Toast.LENGTH_SHORT).show();
                    //    scrollingFunc("");
////                    }
//                }
                    // Get the LayoutManager (assuming you are using LinearLayoutManager)
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                    // Check if the last visible item is at the end of the list
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                    // If the user reaches the end of the list, load more items
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                        // You can trigger the data loading here
                        displayImagesInRecyclerView();
                    }
                }

                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        // You can check when scrolling is idle here, if needed
                    }
                }
            });
//        }
*/


    }




    private void scrollingFunc(String newText) {
//

        String str = newText.toLowerCase();
        viewModel.getScrolledImages(str).observe(this, new Observer<List<ImageM>>() {
            @Override
            public void onChanged(List<ImageM> imagesFromLivedata) {
                images =  (ArrayList<ImageM>) imagesFromLivedata;
                addImagesToRecyclerView();
            }
        });
    }
    private void addImagesToRecyclerView() {
        imageAdapter = new ImageMAdapter(this, images); //new ArrayList<Image>(combinedList)); //new ImageAdapter(this, images);
//        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(imageAdapter);
        imageAdapter.notifyDataSetChanged();
    }

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            String newText = searchView.getQuery().toString();
            filterImages(newText);
        }
    };

    private void filterImages(String filteringImgText){
        String str = filteringImgText.toLowerCase();
        viewModel.getFilteredImages(str).observe(this, new Observer<List<ImageM>>() {
            @Override
            public void onChanged(List<ImageM> imagesFromLivedata) {
                images =  (ArrayList<ImageM>) imagesFromLivedata;
                displayImagesInRecyclerView();
            }
        });

    }

    private void getTrendingImages() {
        viewModel.getAllImages().observe(this, new Observer<List<ImageM>>() {
            @Override
            public void onChanged(List<ImageM> imagesFromLivedata) {
                images =  (ArrayList<ImageM>) imagesFromLivedata;

                displayImagesInRecyclerView();
            }
        });

    }

    private void displayImagesInRecyclerView() {

        recyclerView = binding.mainRecyclerView;
        imageAdapter = new ImageMAdapter(this, images);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(imageAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        imageAdapter.notifyDataSetChanged();
        // imageAdapter.notifyAll();


    }
}