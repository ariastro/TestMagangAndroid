package com.astronout.testmagangdot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.astronout.testmagangdot.adapter.MalangAdapter.EXTRA_CAPTION;
import static com.astronout.testmagangdot.adapter.MalangAdapter.EXTRA_IMAGE;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.detail_caption)
    TextView detailCaption;
    @BindView(R.id.detail_img)
    ImageView detailImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String img = intent.getStringExtra(EXTRA_IMAGE);
        String caption = intent.getStringExtra(EXTRA_CAPTION);

        Glide.with(this)
                .load(img)
                .into(detailImg);

        detailCaption.setText(caption);

    }
}
