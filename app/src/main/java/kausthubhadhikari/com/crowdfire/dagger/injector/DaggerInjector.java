package kausthubhadhikari.com.crowdfire.dagger.injector;

import kausthubhadhikari.com.crowdfire.AppController;
import kausthubhadhikari.com.crowdfire.dagger.component.AppComponent;
import kausthubhadhikari.com.crowdfire.view.MainActivity;

/**
 * Created by kausthubhadhikari on 20/12/16.
 */

public class DaggerInjector implements Injector {

    private AppComponent appComponent;

    @Override
    public void init(AppController appController) {

    }

    @Override
    public void inject(MainActivity activity) {

    }
}
