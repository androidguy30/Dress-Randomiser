package kausthubhadhikari.com.crowdfire.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
import kausthubhadhikari.com.crowdfire.model.pojo.GarmentPojo;
import kausthubhadhikari.com.crowdfire.presenter.MainPresenter;
import kausthubhadhikari.com.crowdfire.utils.adapter.ViewpagerAdapter;
import kausthubhadhikari.com.crowdfire.utils.base.BaseActivity;
import kausthubhadhikari.com.crowdfire.utils.misc.AppUtils;

public class MainActivity extends BaseActivity implements MainView {

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
    public void addUpperGarment(GarmentPojo data) {

    }

    @Override
    public void addLowerGarment(GarmentPojo data) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @OnClick({R.id.addShirt, R.id.addPant, R.id.shuffle, R.id.fav})
    public void onClick(View view) {
        if (R.id.addPant == view.getId()) {

        } else if (R.id.addShirt == view.getId()) {
            //       presenter.addData();
        } else if (R.id.shuffle == view.getId()) {

        } else if (R.id.fav == view.getId()) {
            favButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);

        }
    }
}
