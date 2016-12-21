package kausthubhadhikari.com.crowdfire.utils.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import kausthubhadhikari.com.crowdfire.model.pojo.GarmentPojo;

/**
 * Created by kausthubhadhikari on 21/12/16.
 */

public class ViewpagerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<GarmentPojo> garments;

    public ViewpagerAdapter(Context context, ArrayList<GarmentPojo> garments) {
        this.context = context;
        this.garments = garments;
    }

    @Override
    public int getCount() {
        return garments.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(garments.get(position).imageData, 0, garments.get(position).imageData.length));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        container.addView(imageView);
        return container;
    }

    public void updateAll(ArrayList<GarmentPojo> data) {
        garments.clear();
        garments.addAll(data);
        notifyDataSetChanged();
    }

    public void addItem(GarmentPojo data) {
        garments.add(data);
        notifyDataSetChanged();
    }

    public GarmentPojo getItem(int position) {
        return garments.get(position);
    }
}
