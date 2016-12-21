package kausthubhadhikari.com.crowdfire.dagger.injector;

import kausthubhadhikari.com.crowdfire.AppController;
import kausthubhadhikari.com.crowdfire.view.MainActivity;

/**
 * Created by kausthubhadhikari on 20/12/16.
 */

public interface Injector {

    void init(AppController appController);

    void inject(MainActivity activity);

}
