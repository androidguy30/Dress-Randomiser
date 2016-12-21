package kausthubhadhikari.com.crowdfire.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import kausthubhadhikari.com.crowdfire.R;
import kausthubhadhikari.com.crowdfire.presenter.MainPresenter;
import kausthubhadhikari.com.crowdfire.utils.base.BaseActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public MainPresenter getPresenter() {
        return null;
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

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showSnackbar(String message) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}
