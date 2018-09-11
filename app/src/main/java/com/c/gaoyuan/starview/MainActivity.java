package com.c.gaoyuan.starview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.c.gaoyuan.star_view_lib.StarView;
import com.c.gaoyuan.star_view_lib.StoreItemOnClickListener;

public class MainActivity extends AppCompatActivity
        implements StoreItemOnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("StarViewDemo");

        initStarView();
    }

    private void initStarView() {
        StarView starView = findViewById(R.id.start_view);
        starView.setCheckStarCount(2);
        starView.setCheckStarDrawable(R.drawable.search_result_brands_list_icon_star_y);
        starView.setStarDrawable(R.drawable.search_result_brands_list_icon_star_g);
        starView.setStarCount(5);
        starView.setStarHorizontalSpace(50);
        starView.setStarWidth(150);
        starView.setStarHeight(150);

        starView.refreshView();

        starView.setStoreItemOnClickListener(this);
    }

    @Override
    public void onClick(int index) {
        Toast.makeText(this, "index: " + index, Toast.LENGTH_SHORT).show();
    }
}
