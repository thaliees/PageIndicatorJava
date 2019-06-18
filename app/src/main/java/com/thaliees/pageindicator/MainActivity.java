package com.thaliees.pageindicator;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
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
}

