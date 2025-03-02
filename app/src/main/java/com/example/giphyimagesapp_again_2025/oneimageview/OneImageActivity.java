package com.example.giphyimagesapp_again_2025.oneimageview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.giphyimagesapp_again_2025.R;
import com.example.giphyimagesapp_again_2025.databinding.ActivityOneImageBinding;
import com.example.giphyimagesapp_again_2025.models.ImageM;
import com.example.giphyimagesapp_again_2025.viewmodels.MainActivityViewModel;

public class OneImageActivity extends AppCompatActivity {

    private ImageView oneImgView;
    private TextView oneImgTextView;

    private ActivityOneImageBinding oneImageBinding;

    private ConstraintLayout constraintLayout;

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_image);

        oneImgTextView = (TextView) findViewById(R.id.oneTextView);
        oneImgView = (ImageView) findViewById(R.id.oneImgView);

        Intent intent = getIntent();
        String oneImgTitle = intent.getStringExtra("oneImageTitle");
        String oneImgId = intent.getStringExtra("imgId");

        oneImgTextView.setText(oneImgTitle);
        String oneImgUrl = "https://media2.giphy.com/media/" +oneImgId+ "/giphy.gif?cid=76cf7ff42d80c1n64dtjicdp9bnmq8ybj3bt1lciqhhb8v1h&ep=v1_gifs_trending&rid=giphy.gif&ct=g";
        //  Toast.makeText(OneImageActivity.this, ""+intent.getStringExtra("imgUrl"), Toast.LENGTH_SHORT).show();
        Glide.with(this)
                .load(oneImgUrl)
                .into(oneImgView);

    }
}