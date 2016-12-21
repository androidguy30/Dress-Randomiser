package kausthubhadhikari.com.crowdfire;

import android.app.Application;

import kausthubhadhikari.com.crowdfire.dagger.injector.DaggerInjector;
import kausthubhadhikari.com.crowdfire.dagger.injector.Injector;

/**
 * Created by kausthubhadhikari on 20/12/16.
 */

public class AppController extends Application {

    private Injector injector;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    public void initDagger() {
        getDaggerInjector().init(this);
    }

    public Injector getDaggerInjector() {
        if (injector == null) {
            injector = new DaggerInjector();
        }
        return injector;
    }
}
