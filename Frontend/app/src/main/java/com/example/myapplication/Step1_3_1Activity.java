package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class Step1_3_1Activity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout indicatorLayout;
    private TextView storeDescription;
    private Button contactButton;
    private List<Integer> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step1_3_1);

        viewPager = findViewById(R.id.viewPager);
        indicatorLayout = findViewById(R.id.indicatorLayout);
        storeDescription = findViewById(R.id.storeDescription);
        contactButton = findViewById(R.id.contactButton);

        images = new ArrayList<>();
        images.add(R.drawable.in_img1);
        images.add(R.drawable.in_img2);
        images.add(R.drawable.in_img3);

        ImagePagerAdapter adapter = new ImagePagerAdapter(this, images);
        viewPager.setAdapter(adapter);

        setupIndicator(images.size());

        contactButton.setOnClickListener(v -> {
            Intent intent = new Intent(Step1_3_1Activity.this, ContactOfficial1Activity.class);
            startActivity(intent);
        });
    }

    private void setupIndicator(int count) {
        for (int i = 0; i < count; i++) {
            TextView dot = new TextView(this);
            dot.setText("●");
            dot.setTextSize(12);
            dot.setTextColor(i == 0 ? getResources().getColor(android.R.color.black) : getResources().getColor(android.R.color.darker_gray));
            indicatorLayout.addView(dot);
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < indicatorLayout.getChildCount(); i++) {
                    TextView dot = (TextView) indicatorLayout.getChildAt(i);
                    dot.setTextColor(i == position ? getResources().getColor(android.R.color.black) : getResources().getColor(android.R.color.darker_gray));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }
}
