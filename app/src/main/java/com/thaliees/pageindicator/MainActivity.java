package com.thaliees.pageindicator;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ViewPager.PageTransformer {
    private LinearLayout pageIndicator;
    private TextView[] indicators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create and add Adapter for our views
        ViewPager container = findViewById(R.id.containerPage);
        PageView page = new PageView(this);
        container.setAdapter(page);
        // Add Transformer for when change page
        // true if the supplied PageTransformer requires page views to be drawn from last to first instead of first to last.
        container.setPageTransformer(false, this);
        container.addOnPageChangeListener(pageIndicatorChange);

        pageIndicator = findViewById(R.id.pageIndicator);
        addIndicators(page.getCount());
        indicatorSelected(0);
    }

    private ViewPager.OnPageChangeListener pageIndicatorChange = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            indicatorSelected(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    private void addIndicators(Integer total){
        // Initialize Array with the same number views in our PageView
        indicators = new TextView[total];
        // Edit indicators
        for (int i = 0; i < total; i++) {
            indicators[i] = new TextView(this);
            indicators[i].setText(Html.fromHtml("&#8226;"));
            indicators[i].setTextSize(30);
            indicators[i].setTextColor(getResources().getColor(R.color.colorPrimary));
            pageIndicator.addView(indicators[i]);
        }
    }

    private void indicatorSelected(Integer position){
        if (indicators.length > 0) {
            // Change color to all indicators
            for (TextView a : indicators) {
                a.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
            // Change color only to the indicator selected
            indicators[position].setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }

    @Override
    public void transformPage(@NonNull View view, float position) {
        // Here, all page are in the same screen
        view.setTranslationX(view.getWidth() * - position);

        // Transition: Called for each attached page whenever the scroll position is changed
        if (position == 0F) view.setAlpha(1F);                          // View will be totally clear
        else if (position <= -1F || position >= 1F) view.setAlpha(0F);  // View will not be seen
        else view.setAlpha(1F - Math.abs(position));                    // View will be opaque
    }
}

