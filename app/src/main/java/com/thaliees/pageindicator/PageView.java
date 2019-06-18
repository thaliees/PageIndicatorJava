package com.thaliees.pageindicator;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PageView extends PagerAdapter {
    private Context context;
    private static int[] images = { R.drawable.developer, R.drawable.code };
    private static int[] titles = { R.string.page_one, R.string.page_two };
    private static int[] descriptions = { R.string.page_one_description, R.string.page_two_description };

    public PageView(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Create view to show
        View pagerView = layoutInflater.inflate(R.layout.tutorial, container, false);
        // Set resources
        ImageView image = pagerView.findViewById(R.id.image);
        image.setImageDrawable(context.getDrawable(images[position]));
        TextView text = pagerView.findViewById(R.id.text);
        text.setText(context.getString(titles[position]));
        TextView description = pagerView.findViewById(R.id.description);
        description.setText(descriptions[position]);
        // Add view to container
        container.addView(pagerView);
        return pagerView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
