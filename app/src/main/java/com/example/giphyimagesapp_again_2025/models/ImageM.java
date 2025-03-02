package com.example.giphyimagesapp_again_2025.models;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageM extends BaseObservable {
    public ImageM() {
    }

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String imgUrl;
    @SerializedName("title")
    @Expose
    private String title;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @BindingAdapter({"bind:id"})
    public static void loadImage(ImageView imageView, String id) {
        String imagePath = "https://media2.giphy.com/media/" +id+ "/giphy.gif?cid=76cf7ff42d80c1n64dtjicdp9bnmq8ybj3bt1lciqhhb8v1h&ep=v1_gifs_trending&rid=giphy.gif&ct=g";
        //"https://media4.giphy.com/media/BLyxWCJPOROU82DQGv/giphy-downsized-medium.gif?cid=76cf7ff42d80c1n64dtjicdp9bnmq8ybj3bt1lciqhhb8v1h&ep=v1_gifs_trending&rid=giphy-downsized-medium.gif&ct=g";//"https://giphy.com/gifs/"+imageUrl;
        Glide.with(imageView.getContext())
                .load(imagePath)
                .into(imageView);
    }



//    @Bindable
//    public String getImgUrl() {
//        return imgUrl;
//    }
//
//    public void setImgUrl(String imgUrl) {
//        this.imgUrl = imgUrl;
//        notifyPropertyChanged(BR.imgUrl);
//    }

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
       // notifyPropertyChanged(BR.id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
