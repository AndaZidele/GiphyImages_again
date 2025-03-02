package com.example.giphyimagesapp_again_2025.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giphyimagesapp_again_2025.MainActivity;
import com.example.giphyimagesapp_again_2025.R;
import com.example.giphyimagesapp_again_2025.databinding.ImageItemLayoutBinding;
import com.example.giphyimagesapp_again_2025.models.ImageM;
import com.example.giphyimagesapp_again_2025.oneimageview.OneImageActivity;

import java.util.ArrayList;

public class ImageMAdapter extends RecyclerView.Adapter<ImageMAdapter.ImageViewHolder> {

    private Context context;
    private ArrayList<ImageM> imageArrayList;

    public ImageMAdapter(Context context, ArrayList<ImageM> imageArrayList) {
        this.context = context;
        this.imageArrayList = imageArrayList;
    }

    @NonNull
    @Override
    public ImageMAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageItemLayoutBinding binding = DataBindingUtil
                .inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.image_item_layout, parent, false
                );
        return new ImageMAdapter.ImageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageMAdapter.ImageViewHolder holder, int position) {
        ImageM image = imageArrayList.get(position);
        holder.itemLayoutBinding.setImage(image);
    }

    @Override
    public int getItemCount() {
        return imageArrayList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        private ImageItemLayoutBinding itemLayoutBinding;

        public ImageViewHolder(ImageItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding = itemLayoutBinding;

            itemLayoutBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, OneImageActivity.class);
                    intent.putExtra("oneImageTitle",itemLayoutBinding.getImage().getTitle());
                    //intent.putExtra("imgUrl",itemLayoutBinding.getImage().getImgUrl());
                    intent.putExtra("imgId", itemLayoutBinding.getImage().getId());

                    context.startActivity(intent);

                }
            });
        }
    }
}
