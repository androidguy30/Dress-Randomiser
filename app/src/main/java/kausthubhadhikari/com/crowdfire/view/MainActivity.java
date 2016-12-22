package kausthubhadhikari.com.crowdfire.view;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v13.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kausthubhadhikari.com.crowdfire.R;
import kausthubhadhikari.com.crowdfire.dagger.injector.Injector;
import kausthubhadhikari.com.crowdfire.model.pojo.FavGarmentPojo;
import kausthubhadhikari.com.crowdfire.model.pojo.GarmentPojo;
import kausthubhadhikari.com.crowdfire.presenter.MainPresenter;
import kausthubhadhikari.com.crowdfire.utils.adapter.ViewpagerAdapter;
import kausthubhadhikari.com.crowdfire.utils.base.BaseActivity;
import kausthubhadhikari.com.crowdfire.utils.misc.AppConstants;
import kausthubhadhikari.com.crowdfire.utils.misc.AppUtils;

public class MainActivity extends BaseActivity implements MainView, ViewPager.OnPageChangeListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.shuffle)
    FloatingActionButton shuffleFAB;

    @Bind(R.id.addPant)
    FloatingActionButton addPant;

    @Bind(R.id.addShirt)
    FloatingActionButton addShirt;

    @Bind(R.id.fav)
    FloatingActionButton favButton;

    @Bind(R.id.viewPager1)
    ViewPager upperPager;

    @Bind(R.id.viewPager2)
    ViewPager lowerPager;

    @Inject
    MainPresenter presenter;

    @Inject
    AppUtils appUtils;

    @Inject
    MaterialDialog progressDialog;

    @Inject
    ViewpagerAdapter upperViewPagerAdapter, lowerViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void inject(Injector component) {
        component.inject(this);
    }

    @Override
    public MainPresenter getPresenter() {
        return presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setupView() {
        setSupportActionBar(toolbar);

        upperPager.setAdapter(upperViewPagerAdapter);
        lowerPager.setAdapter(lowerViewPagerAdapter);
        lowerPager.addOnPageChangeListener(this);
        upperPager.addOnPageChangeListener(this);
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar.make(addPant, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setUpperGarmentData(ArrayList<GarmentPojo> data) {
        upperViewPagerAdapter.updateAll(data);
    }

    @Override
    public void setLowerGarmentData(ArrayList<GarmentPojo> data) {
        lowerViewPagerAdapter.updateAll(data);
    }

    @Override
    public void favFabState(boolean state) {
        if (state) {
            favButton.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            favButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        GarmentPojo garmentPojo = new GarmentPojo();
        garmentPojo.imageData = AppUtils.bitmap2byte((Bitmap) data.getExtras().get("data"));
        if (requestCode == AppConstants.KEY_REQUEST_ADD_LOWGAR) {
            garmentPojo.type = AppConstants.ClothType.LOWER;
            lowerViewPagerAdapter.addItem(garmentPojo);
        } else if (requestCode == AppConstants.KEY_REQUEST_ADD_UPGAR) {
            garmentPojo.type = AppConstants.ClothType.UPPER;
            upperViewPagerAdapter.addItem(garmentPojo);
        }
        presenter.addData(garmentPojo);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == AppConstants.KEY_REQUEST_ADD_UPGAR) {
            AppUtils.pickingDialog(this, AppConstants.KEY_REQUEST_ADD_UPGAR).show();
        } else if (requestCode == AppConstants.KEY_REQUEST_ADD_LOWGAR) {
            AppUtils.pickingDialog(this, AppConstants.KEY_REQUEST_ADD_LOWGAR).show();
        }
    }

    @OnClick({R.id.addShirt, R.id.addPant, R.id.shuffle, R.id.fav})
    public void onClick(View view) {
        if (R.id.addPant == view.getId()) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, AppConstants.KEY_REQUEST_ADD_LOWGAR);
        } else if (R.id.addShirt == view.getId()) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, AppConstants.KEY_REQUEST_ADD_UPGAR);
        } else if (R.id.shuffle == view.getId()) {

        } else if (R.id.fav == view.getId()) {
            FavGarmentPojo data = new FavGarmentPojo();
            data.up_gar_id = upperViewPagerAdapter.getItem(upperPager.getCurrentItem()).id;
            data.up_grad_image = upperViewPagerAdapter.getItem(upperPager.getCurrentItem()).imageData;
            data.lo_gar_id = lowerViewPagerAdapter.getItem(lowerPager.getCurrentItem()).id;
            data.lo_grad_image = lowerViewPagerAdapter.getItem(lowerPager.getCurrentItem()).imageData;
            presenter.addFavourited(data);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        presenter.isFavourited(lowerViewPagerAdapter.getItem(position).id, upperViewPagerAdapter.getItem(position).id);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
