package com.suleiman.techkriti.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.suleiman.techkriti.R;

/**
 * Created by HP on 2/11/2016.
 */

public class FullScreenImageAdapter1 extends PagerAdapter {

    private Activity _activity;
    int _imagePaths[];
    private LayoutInflater inflater;

    // constructor
    public FullScreenImageAdapter1(Activity activity,
                                  int imagePaths[]) {
        this._activity = activity;
        this._imagePaths = imagePaths;

    }

    @Override
    public int getCount() {
        return _imagePaths.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imgDisplay;

        inflater = (LayoutInflater) _activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.layout_fullscreen_image1, container,
                false);

          ;

        imgDisplay = (ImageView) viewLayout.findViewById(R.id.imgDisplay);
final int pos=position;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
      /*  Picasso.with(_activity)
                .load(_imagePaths[position])
                .into(imgDisplay);*/

        imgDisplay.setBackgroundResource(_imagePaths[position]);


        ((ViewPager) container).addView(viewLayout);

        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}
