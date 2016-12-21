package kausthubhadhikari.com.crowdfire.utils.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import kausthubhadhikari.com.crowdfire.AppController;
import kausthubhadhikari.com.crowdfire.dagger.injector.Injector;

/**
 * Created by kausthubhadhikari on 20/12/16.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(((AppController) getApplicationContext()).getDaggerInjector());
        getPresenter().onViewCreated(savedInstanceState == null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().unsubcribe();
    }

    public abstract void inject(Injector component);

    public abstract P getPresenter();

}
