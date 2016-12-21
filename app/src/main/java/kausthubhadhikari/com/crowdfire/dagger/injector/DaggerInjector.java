package kausthubhadhikari.com.crowdfire.dagger.injector;

import kausthubhadhikari.com.crowdfire.AppController;
import kausthubhadhikari.com.crowdfire.dagger.component.AppComponent;
import kausthubhadhikari.com.crowdfire.dagger.component.DaggerAppComponent;
import kausthubhadhikari.com.crowdfire.dagger.component.DaggerMainComponent;
import kausthubhadhikari.com.crowdfire.dagger.module.AppModule;
import kausthubhadhikari.com.crowdfire.dagger.module.MainModule;
import kausthubhadhikari.com.crowdfire.view.MainActivity;

/**
 * Created by kausthubhadhikari on 20/12/16.
 */

public class DaggerInjector implements Injector {

    private AppComponent appComponent;

    @Override
    public void init(AppController appController) {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(appController))
                .build();
    }

    @Override
    public void inject(MainActivity activity) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(activity))
                .build()
                .inject(activity);
    }
}
