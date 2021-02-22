package com.example.shopisthan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class ShopSlideActivity extends AppCompatActivity
{
    public  static ViewPager viewPager;
    ShopViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_slide);

        viewPager = findViewById(R.id.viewpage);
        adapter = new ShopViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
    }
}